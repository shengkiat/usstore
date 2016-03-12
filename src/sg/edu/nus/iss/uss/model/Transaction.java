package sg.edu.nus.iss.uss.model;

import java.util.Date;

public class Transaction {
	private static int transactionID;
	private String productID;
	private String memberID;
	private int quantityPurchased;
	private Date date;
	
	public int getTransactionID() {
		return transactionID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {//1,CLO/1,F42563743156,2,2013-09-28
		//TODO toString
		
		return "";
	}

}
