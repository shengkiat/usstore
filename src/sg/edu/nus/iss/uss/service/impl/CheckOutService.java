package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.PayItem;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.ICheckOutService;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CheckOutService extends UssCommonService implements ICheckOutService{

    private CheckoutSummary checkoutSummary = null;
    private String memberID = "";//"" means No MemberID

    @Override
    public CheckoutSummary getCheckoutSummary(){
        return this.checkoutSummary;
    }

    @Override
    public String determineMemberID(String memberID){
        //TODO

        this.memberID = memberID;

        return memberID;
    }

    @Override
    public PayItem addItemIntoCheckOutList(String productID, String memberID){

        //TODO Get product by productID

        //TODO get max discount via memberID
        Discount discount = null; //TODO

        //calculate charge price
//		int chargePrice = calculateChargePrice(discount);

        //TODO create PayItem

        if(null == checkoutSummary){
            checkoutSummary = new CheckoutSummary();
            checkoutSummary.setCheckoutItems(new ArrayList<Product>());
        }

        //TODO add PayItem into checkout List
//		checkoutSummary.getCheckoutList().add(e);

        return null;
    }

    @Override
    public List<Product> addItemIntoCheckOutList(Product product, String memberID){

        //TODO Get product by productID

        //TODO get max discount via memberID
        Discount discount = null; //TODO

        //calculate charge price
//        int chargePrice = calculateChargePrice(discount);

        //TODO create PayItem

        if(null == checkoutSummary){
            checkoutSummary = new CheckoutSummary();
            checkoutSummary.setCheckoutItems(new ArrayList<Product>());
        }

        //TODO add PayItem into checkout List
//		checkoutSummary.getCheckoutList().add(e);

        checkoutSummary.getCheckoutItems().add(product);

        return checkoutSummary.getCheckoutItems();
    }

    @Override
    public List<Product> addItemIntoCheckOutList(Product product){

        return this.addItemIntoCheckOutList(product, this.memberID);
    }


    @Override
    public PayItem addItemIntoCheckOutList(String productID){

        return this.addItemIntoCheckOutList(productID, this.memberID);
    }

    @Override
    public String alertIfInventoryLevelBelowThreshold(PayItem payItem){
        String alert = "";

        //TODO if below Threshold, return alert message

        return "";
    }

    @Override
    // change to decimal
    public int makePayment(String payAmount, int redeemPoint){
        //TODO

        return 0;
    }

    @Override
    public String printoutReceipt(CheckoutSummary checkoutSummary){
        //TODO

        return "";
    }

    public double calculateChargePrice(int discount){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        double totalProductPrice = checkoutSummary.getTotalPrice();
        double chargePrice = totalProductPrice * ((100.0 - discount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;

        return chargePrice;
    }

    private void paymentValidation(String payAmount, int redeemPoint, int totalAmount){

        //TODO
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

        return dollarsConverted;
    }


}