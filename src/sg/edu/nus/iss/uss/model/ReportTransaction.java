package sg.edu.nus.iss.uss.model;

import java.util.Date;

public class ReportTransaction {
	
	private final int transactionID;
	private final String productID;
	private final String buyerID;
	private final int quantityPurchased;
	private final Date date;
	private final String productName;
	private final String productBriefDescription;
	
	public ReportTransaction(Transaction transaction, Product product) {
		this.transactionID = transaction.getTransactionID();
		this.productID = transaction.getProductID();
		this.buyerID = transaction.getBuyerID();
		this.quantityPurchased = transaction.getQuantityPurchased();
		this.date = transaction.getDate();
		this.productName = product.getName();
		this.productBriefDescription = product.getBriefDescription();
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

	public String getProductName() {
		return productName;
	}

	public String getProductBriefDescription() {
		return productBriefDescription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportTransaction [transactionID=");
		builder.append(transactionID);
		builder.append(", productID=");
		builder.append(productID);
		builder.append(", buyerID=");
		builder.append(buyerID);
		builder.append(", quantityPurchased=");
		builder.append(quantityPurchased);
		builder.append(", date=");
		builder.append(date);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", productBriefDescription=");
		builder.append(productBriefDescription);
		builder.append("]");
		return builder.toString();
	}
	

}
