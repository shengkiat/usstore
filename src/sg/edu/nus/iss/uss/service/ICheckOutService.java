package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.PayItem;
import sg.edu.nus.iss.uss.model.Product;

import java.util.List;

public interface ICheckOutService {
	
	public CheckoutSummary getCheckoutSummary();
	
	public boolean determineMemberID(String memberID);

	public List<Product> addItemIntoCheckOutList(Product product, String memberID);
	
	public List<Product> addItemIntoCheckOutList(Product product);

	public String alertIfInventoryLevelBelowThreshold(PayItem payItem);

    public double calculateTotalPayableAfterPointsRedemption(double payAmount, int redeemPoint);

    public double makePayment(double payAmount, int redeemPoint) throws UssException;
	
	public String printoutReceipt(CheckoutSummary checkoutSummary);
	
    public int convertDollarToPoint(double dollar);

    public double calculateChargePrice(int discount);

//	public int convertPointToDollar(int point);
	
}
