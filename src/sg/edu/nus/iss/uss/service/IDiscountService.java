package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Discount;

public interface IDiscountService {
	
	public List<Discount> getAll();

	public void AddNewDiscount(String discountCode, String description, double discountPercentage, Date startDate,
			int discountDays) throws UssException;

	public void UpdateDiscount(String discountCode, String description, double discountPercentage, Date startDate,
			int discountDays) throws UssException;

	public void RemoveDiscount(String discountCode) throws UssException;

	public double getMembersTodaysHighestDiscount(Boolean isMember, Boolean isFirstPurchase);
}
