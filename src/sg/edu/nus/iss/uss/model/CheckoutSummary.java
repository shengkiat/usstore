package sg.edu.nus.iss.uss.model;

import java.util.Date;
import java.util.List;

public class CheckoutSummary {
	private List<PayItem> checkoutItems;
	private Date checkoutDate;
	
	public List<PayItem> getCheckoutItems() {
		return checkoutItems;
	}

	public void setCheckoutItems(List<PayItem> checkoutItems) {
		this.checkoutItems = checkoutItems;
	}
	
	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public int getTotalPrice(){ 
		//TODO calculate the total price by checkoutList
		
		return 0;
	}

}
