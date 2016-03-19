package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.PayItem;
import sg.edu.nus.iss.uss.model.Product;

public interface ICheckOutService {
	
	public CheckoutSummary getCheckoutSummary();
	
	public String determineMemberID(String memberID);
	
	public PayItem addItemIntoCheckOutList(String productID, String memberID);
	
	public List<Product> addItemIntoCheckOutList(Product product, String memberID);
	
	public List<Product> addItemIntoCheckOutList(Product product);
	
	public PayItem addItemIntoCheckOutList(String productID);
	
	public String alertIfInventoryLevelBelowThreshold(PayItem payItem);
	
	public int makePayment(String payAmount, int redeemPoint);
	
	public String printoutReceipt(CheckoutSummary checkoutSummary);
	
    public int convertDollarToPoint(int dollar);

	public int convertPointToDollar(int point);
	
}
