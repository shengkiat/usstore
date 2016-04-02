package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Discount;

public interface IDiscountService {
	
	public List<Discount> getAll();

	public void addNewDiscount(String discountCode, String description, double discountPercentage, Date startDate,
			int discountDays) throws UssException;

	public void updateDiscount(String discountCode, String description, double discountPercentage, String startDate,
			String discountDays) throws UssException;

	public double getMembersTodaysHighestDiscount(Boolean isMember, Boolean isFirstPurchase);
	
	public Discount getDiscountByCode(String dicountcode);
}
