package sg.edu.nus.iss.uss.model;

import java.util.Date;

public class Transaction {
	
	private int transactionID;
	private final String productID;
	private final String buyerID;
	private final int quantityPurchased;
	private final Date date;
	
	public Transaction(String productID, String memberID, int quantityPurchased, Date date) {
		this.productID = productID;
		this.buyerID = memberID;
		this.quantityPurchased = quantityPurchased;
		this.date = date;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID =transactionID;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public String getProductID() {
		return productID;
	}
	public String getBuyerID() {
		return buyerID;
	}
	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionID=");
		builder.append(transactionID);
		builder.append(", productID=");
		builder.append(productID);
		builder.append(", buyerID=");
		builder.append(buyerID);
		builder.append(", quantityPurchased=");
		builder.append(quantityPurchased);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	

}
