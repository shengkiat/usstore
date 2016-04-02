package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.CheckoutSummary;
import sg.edu.nus.iss.uss.model.Product;

import java.util.List;

public interface ICheckOutService {
	
	public CheckoutSummary getCheckoutSummary();

	public boolean determineMemberID(String memberID); 
	
	public List<Product> addItemIntoCheckOutList(Product product);

	public List<Product> alertIfInventoryLevelBelowThreshold(List<Product> productItems);

    public double calculateTotalPayable(double payAmount, int dollarsRedeemed);

    public double memberMakePayment(double amountPaid, int redeemPoint) throws UssException;
	
    public double nonMemberMakePayment(double amountPaid) throws UssException;

    public double calculatePayAmount(double discount);

    public int convertDollarToPointForDebit(double dollar);

	public int convertPointToDollarForDebit(int point);

    public int convertDollarToPointForCredit(double dollar);

    public int convertPointToDollarForCredit(int point);

}
