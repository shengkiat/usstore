package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.model.Discount;

public interface IDiscountService {
	
	public List<Discount> getAll();

	public void AddNewDiscount(String discountCode, String description, double discountPercentage, Date startDate,
			int discountDays);

	public void UpdateDiscount(String discountCode, String description, double discountPercentage, Date startDate,
			int discountDays);

	public void RemoveDiscount(String discountCode);

	public double getMembersTodaysHighestDiscount(Boolean isMember, Boolean isFirstPurchase);

	public double getTodaysDiscount();
	
	
}
