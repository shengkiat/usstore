package sg.edu.nus.iss.uss.model;

import java.util.Date;
import java.util.List;

public class CheckoutSummary {
    private String memberID;
	private List<Product> checkoutItems;
	private Date checkoutDate;
    private double totalPrice;
    private double payAmount;
    private double totalPayable;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public List<Product> getCheckoutItems() {
		return checkoutItems;
	}

	public void setCheckoutItems(List<Product> checkoutItems) {
		this.checkoutItems = checkoutItems;
	}
	
	public Date getCheckoutDate() {
		return checkoutDate;
	}

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getTotalPrice() {
        this.totalPrice = 0.0;

        for(int i = 0; i < checkoutItems.size(); i++ ) {
            totalPrice = totalPrice + checkoutItems.get(i).getPrice();
        }

        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
    }
}
