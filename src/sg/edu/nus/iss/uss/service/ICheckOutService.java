package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.PayItem;
import sg.edu.nus.iss.uss.model.Product;

import java.util.List;

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
	
    public int convertDollarToPoint(double dollar);

	public int convertPointToDollar(int point);
	
}
