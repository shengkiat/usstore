package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;

import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.PayItem;

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
		int chargePrice = calculateChargePrice(discount);
		
		//TODO create PayItem
		
		if(null == checkoutSummary){
			checkoutSummary = new CheckoutSummary();
			checkoutSummary.setCheckoutItems(new ArrayList<PayItem>());
		}
		
		//TODO add PayItem into checkout List
//		checkoutSummary.getCheckoutList().add(e);
		
		return null;
	}
	
	public PayItem addItemIntoCheckOutList(String productID){
		
		return this.addItemIntoCheckOutList(productID, this.memberID);
	}
	
	public String alertIfInventoryLevelBelowThreshold(PayItem payItem){
		String alert = "";
		
		//TODO if below Threshold, return alert message
		
		return "";
	}
	
	public int makePayment(String payAmount, int redeemPoint){
		//TODO
		
		return 0;
	}
	
	public String printoutReceipt(CheckoutSummary checkoutSummary){
		//TODO

		return "";
	}
	
	private int calculateChargePrice(Discount discount){
		//TODO
		
		return 0;
	}
	
	private void paymentValidation(String payAmount, int redeemPoint, int totalAmount){
		
		//TODO
	}
	
	
	
}
