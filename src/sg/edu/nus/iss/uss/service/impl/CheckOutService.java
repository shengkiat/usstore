package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.PayItem;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.ICheckOutService;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOutService extends UssCommonService implements ICheckOutService{

    public MemberService memberService;
    public TransactionService transactionService;
    public ProductService productService;
    private CheckoutSummary checkoutSummary = null;
    private String memberID = "";//"" means No MemberID
    private static final String PUBLIC_BUYER = "PUBLIC";

    public CheckOutService() {
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
//        IMemberDataAccess memberDataAccess = null;
//        memberService = new MemberService(memberDataAccess);
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
    public String alertIfInventoryLevelBelowThreshold(PayItem payItem){
        String alert = "";

        //TODO if below Threshold, return alert message

        return "";
    }

    // new method
    @Override
    public double calculateTotalPayableAfterPointsRedemption(double payAmount, int redeemPoint) {
        double dollarsRedeemed = convertPointToDollar(redeemPoint);
        checkoutSummary.setPayAmount(payAmount - dollarsRedeemed);

        return checkoutSummary.getPayAmount();
    }

    @Override
    public double makePayment(double totalPayable, int redeemPoint) throws UssException{
        double dollarsRedeemed = convertPointToDollar(redeemPoint);
        boolean paymentValidated = paymentValidation(checkoutSummary.getPayAmount(), redeemPoint, totalPayable);

        if(paymentValidated) {
            //minus points from member
            memberService.deductMemberLoyltyPoint(redeemPoint, checkoutSummary.getMemberID());

            // add points to member for purchase
            int pointsAdded = convertDollarToPoint(totalPayable);
            memberService.addMemberLoyaltyPoint(pointsAdded, checkoutSummary.getMemberID());

            // create transaction for buyer
//            transactionService = new TransactionService();


            // deduct from inventory
//            productService.deductInventoryFromCheckout(checkoutSummary.getCheckoutItems());

            // return threshold notification

        } else {
            throw new UssException(ErrorConstants.UssCode.CHECKOUT, ErrorConstants.PAYMENT_VALIDATION_FAIL);
        }

        
        return dollarsRedeemed;
    }

    @Override
    public String printoutReceipt(CheckoutSummary checkoutSummary){
        // print the checkoutsummary

        return "";
    }

    @Override
    public double calculateChargePrice(int discount){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        double totalProductPrice = checkoutSummary.getTotalPrice();
        double chargePrice = totalProductPrice * ((100.0 - discount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        checkoutSummary.setPayAmount(chargePrice);

        return checkoutSummary.getPayAmount();
    }

    private boolean paymentValidation(double payAmount, int redeemPoint, double totalPayable){
        double dollarsRedeemed = convertPointToDollar(redeemPoint);
        if((payAmount - dollarsRedeemed) == totalPayable) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int convertDollarToPoint(double dollar){
        int dollarToPoint = 10;
        int pointsConverted = (int) dollar / 10;

        return pointsConverted;
    }

//    @Override
    private int convertPointToDollar(int point){
        int pointsToDollar = 20;
        int dollarsConverted = point / 20;

        int pointsUsed = dollarsConverted * pointsToDollar;

        return dollarsConverted;
    }


}