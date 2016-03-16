package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.PayItem;
import sg.edu.nus.iss.uss.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CheckOutService extends UssCommonService {
	
private CheckoutSummary checkoutSummary = null;
private String memberID = "";//"" means No MemberID

	public CheckoutSummary getCheckoutSummary(){
		return this.checkoutSummary;
	}
	
	public String determineMemberID(String memberID){
		//TODO
		
		this.memberID = memberID;
		
		return memberID;
	}
	
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

    public List<Product> addItemIntoCheckOutList(Product product){

        return this.addItemIntoCheckOutList(product, this.memberID);
    }

	public PayItem addItemIntoCheckOutList(String productID){
		
		return this.addItemIntoCheckOutList(productID, this.memberID);
	}
	
	public String alertIfInventoryLevelBelowThreshold(PayItem payItem){
		String alert = "";
		
		//TODO if below Threshold, return alert message
		
		return "";
	}

    // change to decimal
	public int makePayment(String payAmount, int redeemPoint){
		//TODO
		
		return 0;
	}
	
	public String printoutReceipt(CheckoutSummary checkoutSummary){
		//TODO

		return "";
	}
	
	private double calculateChargePrice(Discount discount){
		//TODO
		
		return 0;
	}
	
	private void paymentValidation(String payAmount, int redeemPoint, int totalAmount){
		
		//TODO
	}
	
	
	
}
