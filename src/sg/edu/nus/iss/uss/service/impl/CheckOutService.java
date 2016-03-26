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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOutService extends UssCommonService implements ICheckOutService{

    private IMemberService memberService;
    private ITransactionService transactionService;
    public IProductService productService;
    private CheckoutSummary checkoutSummary;
    private String memberID = "";//"" means No MemberID
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
        }
        else {
            checkoutSummary.setMemberID(PUBLIC_BUYER);
        }

        return isValidMember;
    }


    @Override
    public List<Product> addItemIntoCheckOutList(Product product, String memberID){
        checkoutSummary.getCheckoutItems().add(product);
        return checkoutSummary.getCheckoutItems();
    }

    @Override
    public List<Product> addItemIntoCheckOutList(Product product){
        return this.addItemIntoCheckOutList(product, this.memberID);
    }

    @Override
    public List<Product> alertIfInventoryLevelBelowThreshold(List<Product> productItems){
        boolean isBelowThreshold = false;
        List<Product> listOfProductsBelowThreshold = new ArrayList<Product>();

        for(int i = 0; i < productItems.size(); i++) {
            isBelowThreshold = productService.checkIfProductIsBelowThreshold(productItems.get(i));
            if(isBelowThreshold) {
                listOfProductsBelowThreshold.add(productItems.get(i));
            }
        }

        return listOfProductsBelowThreshold;
    }

    @Override
    public double calculateTotalPayable(double payAmount, int redeemPoint) {
        double dollarsRedeemed = convertPointToDollar(redeemPoint);
        checkoutSummary.setTotalPayable(payAmount - dollarsRedeemed);

        return checkoutSummary.getTotalPayable();
    }

    @Override
    public double makePayment(double amountPaid, int redeemPoint) throws UssException{
        double dollarsRedeemed = convertPointToDollar(redeemPoint);
        boolean paymentValidated = paymentValidation(checkoutSummary.getPayAmount(), redeemPoint, checkoutSummary.getTotalPayable(),amountPaid);
        double returnChange = amountPaid - checkoutSummary.getTotalPayable();


        memberService.deductMemberLoyltyPoint(redeemPoint, checkoutSummary.getMemberID());

        int pointsAdded = convertDollarToPoint(checkoutSummary.getTotalPayable());
        memberService.addMemberLoyaltyPoint(pointsAdded, checkoutSummary.getMemberID());

        // create transaction for buyer
        transactionService.createTransactions(checkoutSummary.getCheckoutItems(), checkoutSummary.getMemberID(), checkoutSummary.getCheckoutDate());

        // deduct from inventory
        productService.deductInventoryFromCheckout(checkoutSummary.getCheckoutItems());

        // return threshold notification
        alertIfInventoryLevelBelowThreshold(checkoutSummary.getCheckoutItems());


        return returnChange;
    }

    @Override
    public String printoutReceipt(CheckoutSummary checkoutSummary){
        // print the checkoutsummary

        return "";
    }

    @Override
    public double calculatePayAmount(double discount){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        double totalProductPrice = checkoutSummary.getTotalPrice();
        double chargePrice = totalProductPrice * ((100.0 - discount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        checkoutSummary.setPayAmount(chargePrice);

        return checkoutSummary.getPayAmount();
    }

    private boolean paymentValidation(double payAmount, int redeemPoint, double totalPayable, double amountPaid) throws UssException {
        double dollarsRedeemed = convertPointToDollar(redeemPoint);
        double returnChange = (amountPaid - totalPayable);

        if(returnChange < 0.0) {
            throw new UssException(ErrorConstants.UssCode.CHECKOUT, ErrorConstants.AMOUNT_RECEIVED_LESS_THAN_TOTAL_PAYABLE);
        }
//        if((payAmount - dollarsRedeemed) == totalPayable) {
//            throw new UssException(ErrorConstants.UssCode.CHECKOUT, ErrorConstants.PAYMENT_VALIDATION_FAIL);
//        }
//
        return true;
    }

    @Override
    public int convertDollarToPoint(double dollar){
        int dollarToPoint = 10;
        int pointsConverted = (int) dollar / 10;

        return pointsConverted;
    }

    @Override
    public int convertPointToDollar(int point){
        int pointsToDollar = 20;
        int dollarsConverted = point / 20;

        int pointsUsed = dollarsConverted * pointsToDollar;

        return dollarsConverted;
    }


}
