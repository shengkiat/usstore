package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.ICheckOutService;
import sg.edu.nus.iss.uss.service.IMemberService;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.ITransactionService;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

public class CheckOutService extends UssCommonService implements ICheckOutService{

    private IMemberService memberService;
    private ITransactionService transactionService;
    private IProductService productService;
    private CheckoutSummary checkoutSummary;
    private static final String PUBLIC_BUYER = "PUBLIC";

    public CheckOutService(IMemberService memberService, ITransactionService transactionService, IProductService productService) {
        this.memberService = memberService;
        this.transactionService = transactionService;
        this.productService = productService;

        checkoutSummary = new CheckoutSummary();
        Date date= new Date();
        checkoutSummary.setCheckoutDate(new Timestamp(date.getTime()));
        checkoutSummary.setCheckoutItems(new ArrayList<Product>());
    }

    @Override
    public CheckoutSummary getCheckoutSummary(){
        return this.checkoutSummary;
    }

    @Override
    public boolean determineMemberID(String memberID){
    	boolean isValidMember = memberService.isValidMember(memberID);

        if(isValidMember) {
            checkoutSummary.setMemberID(memberID);
            return true;
        }
        else {
        	checkoutSummary.setMemberID(PUBLIC_BUYER);
            return false; 
        }
    }


    @Override
    public List<Product> addItemIntoCheckOutList(Product product){
        checkoutSummary.getCheckoutItems().add(product);
        return checkoutSummary.getCheckoutItems();
    }

    @Override
    public List<Product> alertIfInventoryLevelBelowThreshold(List<Product> productItems){
        boolean isBelowThreshold;
        Set<Product> setOfProductsBelowThreshold = new HashSet<>();

        for(Product product: productItems) {
            isBelowThreshold = productService.checkIfProductIsBelowThreshold(product);
            if(isBelowThreshold) {
                setOfProductsBelowThreshold.add(product);
            }
        }

        List<Product> productsBelowThreshold = new ArrayList<>();
        productsBelowThreshold.addAll(setOfProductsBelowThreshold);

        return productsBelowThreshold;
    }

    @Override
    public double calculateTotalPayable(double payAmount, int dollarsRedeemed) {
        checkoutSummary.setTotalPayable(payAmount - dollarsRedeemed);

        return checkoutSummary.getTotalPayable();
    }

    @Override
    public double memberMakePayment(double amountPaid, int redeemDollar) throws UssException{
        int pointsConverted = convertDollarToPointForDebit(redeemDollar);

//        convertPointToDollarForDebit(redeemDollar);
        paymentValidation(checkoutSummary.getPayAmount(), pointsConverted, checkoutSummary.getTotalPayable(),amountPaid);
        double returnChange = amountPaid - checkoutSummary.getTotalPayable();

        memberService.deductMemberLoyltyPoint(pointsConverted, checkoutSummary.getMemberID());

        int pointsAdded = convertDollarToPointForCredit(checkoutSummary.getTotalPayable());
        memberService.addMemberLoyaltyPoint(pointsAdded, checkoutSummary.getMemberID());

        // create transaction for buyer
        transactionService.createTransactions(checkoutSummary.getCheckoutItems(), checkoutSummary.getMemberID(), checkoutSummary.getCheckoutDate());

        // deduct from inventory
       productService.deductInventoryFromCheckout(checkoutSummary.getCheckoutItems());
        returnChange = roundAmount(returnChange);
        return returnChange;
    }

    @Override
    public double nonMemberMakePayment(double amountPaid) throws UssException{

        paymentValidation(checkoutSummary.getPayAmount(), 0, checkoutSummary.getTotalPayable(), amountPaid);
        double returnChange = amountPaid - checkoutSummary.getTotalPayable();

        // create transaction for buyer
        transactionService.createTransactions(checkoutSummary.getCheckoutItems(), PUBLIC_BUYER, checkoutSummary.getCheckoutDate());

        // deduct from inventory
       productService.deductInventoryFromCheckout(checkoutSummary.getCheckoutItems());

        returnChange = roundAmount(returnChange);
        return returnChange;
    }

    @Override
    public double calculatePayAmount(double discount){

        double totalProductPrice = checkoutSummary.getTotalPrice();
        double chargePrice = totalProductPrice * ((100.0 - discount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        checkoutSummary.setPayAmount(chargePrice);

        return checkoutSummary.getPayAmount();
    }

    private double roundAmount(double amountToRound) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        amountToRound = Double.parseDouble(df.format(amountToRound));

        return amountToRound;
    }

    private boolean paymentValidation(double payAmount, int redeemPoint, double totalPayable, double amountPaid) throws UssException {
        double dollarsRedeemed = convertPointToDollarForDebit(redeemPoint);
        double returnChange = (amountPaid - totalPayable);

        // validate if total payable is correct
        double toValidateTotalPayable = payAmount - dollarsRedeemed;
        if(toValidateTotalPayable != totalPayable) {
            throw new UssException(ErrorConstants.UssCode.CHECKOUT, ErrorConstants.DEDUCT_AMOUNT_FOR_TOTAL_PAYABLE_ERROR);
        }

        // validate change given
        if(returnChange < 0.0) {
            throw new UssException(ErrorConstants.UssCode.CHECKOUT, ErrorConstants.AMOUNT_RECEIVED_LESS_THAN_TOTAL_PAYABLE);
        }

        return true;
    }

    @Override
    public int convertDollarToPointForDebit(double dollar){
        int pointsToDollar = 20;
        return (int) dollar * pointsToDollar;
    }

    @Override
    public int convertPointToDollarForDebit(int point){
        int pointsToDollar = 20;
        return point / pointsToDollar;
    }

    @Override
    public int convertDollarToPointForCredit(double dollar){
        int dollarToPoint = 10;
        return (int) dollar / dollarToPoint;
    }

    @Override
    public int convertPointToDollarForCredit(int point){
        int dollarToPoint = 10;
        return point * dollarToPoint;
    }



}
