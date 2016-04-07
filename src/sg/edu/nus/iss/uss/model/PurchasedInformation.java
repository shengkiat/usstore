package sg.edu.nus.iss.uss.model;

import java.util.List;

public class PurchasedInformation {
	
	private final List<String> items;
	private final double subTotal;
	private final double dollarToRedeem;
	private final int pointsAdded;
	private final int loyaltyPointRemained;
	private final double amountReceived;
	private final double change;
	
	public PurchasedInformation(List<String> items, double subTotal, double amountReceived, double change) {
		this(items, subTotal, amountReceived, change, -1, -1, -1);
	}
	
	public PurchasedInformation(List<String> items, double subTotal, double amountReceived, double change, 
			double dollarToRedeem, int pointsAdded, int loyaltyPointRemained) {
		this.items = items;
		this.subTotal = subTotal;
		this.dollarToRedeem = dollarToRedeem;
		this.pointsAdded = pointsAdded;
		this.loyaltyPointRemained = loyaltyPointRemained;
		this.amountReceived = amountReceived;
		this.change = change;
	}
	
	public List<String> getItems() {
		return items;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public double getDollarToRedeem() {
		return dollarToRedeem;
	}
	public int getPointsAdded() {
		return pointsAdded;
	}
	public int getLoyaltyPointRemained() {
		return loyaltyPointRemained;
	}
	public double getAmountReceived() {
		return amountReceived;
	}
	public double getChange() {
		return change;
	}
	
	
}
