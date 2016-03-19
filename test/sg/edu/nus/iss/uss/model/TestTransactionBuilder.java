package sg.edu.nus.iss.uss.model;

import java.util.Date;

public class TestTransactionBuilder {
	
	private int transactionID;
	private String productID = "CLO/1";
	private String buyerID = PublicBuyer.PUBLIC_NAME;
	private int quantityPurchased = 1;
	private Date date = new Date();
	
	public TestTransactionBuilder withTransactionID(int transactionId) {
		this.transactionID = transactionId;
		return this;
	}
	
	public TestTransactionBuilder withProductID(String productID) {
		this.productID = productID;
		return this;
	}
	
	public TestTransactionBuilder withBuyerID(String buyerID) {
		this.buyerID = buyerID;
		return this;
	}
	
	public TestTransactionBuilder withQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
		return this;
	}
	
	public TestTransactionBuilder withDate(Date date) {
		this.date = date;
		return this;
	}
	
	public Transaction build() {
		Transaction transaction = new Transaction(productID, buyerID, quantityPurchased, date);
		transaction.setTransactionID(transactionID);
		return transaction;
	}
	
}
