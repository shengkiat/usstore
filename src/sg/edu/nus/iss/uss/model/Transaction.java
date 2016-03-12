package sg.edu.nus.iss.uss.model;

import java.util.Date;

public class Transaction {
	
	private int transactionID;
	private final String productID;
	private final String memberID;
	private final int quantityPurchased;
	private final Date date;
	
	public Transaction(String productID, String memberID, int quantityPurchased, Date date) {
		this.productID = productID;
		this.memberID = memberID;
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
	public String getMemberID() {
		return memberID;
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
		builder.append(", memberID=");
		builder.append(memberID);
		builder.append(", quantityPurchased=");
		builder.append(quantityPurchased);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	

}
