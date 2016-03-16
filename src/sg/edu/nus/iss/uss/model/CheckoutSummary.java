package sg.edu.nus.iss.uss.model;

import java.util.Date;
import java.util.List;

public class CheckoutSummary {
	private List<Product> checkoutItems;
	private Date checkoutDate;
	
	public List<Product> getCheckoutItems() {
		return checkoutItems;
	}

	public void setCheckoutItems(List<Product> checkoutItems) {
		this.checkoutItems = checkoutItems;
	}
	
	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public double getTotalPrice(){

        double totalPrice = 0.0;

        for(int i = 0; i < checkoutItems.size(); i++ ) {
            totalPrice = totalPrice + checkoutItems.get(i).getPrice();
        }
		
		return totalPrice;
	}

}
