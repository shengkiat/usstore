package sg.edu.nus.iss.uss.service.impl;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.IDiscountDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.service.IDiscountService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class DiscountService extends UssCommonService implements IDiscountService{
	private static final String MEMBER_FIRST_DISCOUNT = "MEMBER_FIRST";
	private static final String MEMBER_SUBSEQ_DISCOUNT = "MEMBER_SUBSEQ";

	private IDiscountDataAccess discountFileAccess;
	
	public DiscountService(IDiscountDataAccess discountFileAccess) {
		this.discountFileAccess = discountFileAccess;
	}
	
	public List<Discount> getAll() {
		return discountFileAccess.getAll();
	}
	
	public void addNewDiscount(String discountCode, String description, double discountPercentage, Date startDate, int discountDays) throws UssException {
		DaySpecialDiscount temp = new DaySpecialDiscount(discountCode, description, discountPercentage, startDate, discountDays);
		discountFileAccess.create(temp);
	}
	
	public void updateDiscount(String discountCode, String description, double discountPercentage, String startDate, String discountDays) throws UssException {
		Discount discount = discountFileAccess.getDiscountByDiscountCode(discountCode);
		if(discount instanceof MemberOnlyDiscount) {
			MemberOnlyDiscount moDis = (MemberOnlyDiscount)discount;
			moDis.setDescription(description);
			moDis.setDiscountPercentage(discountPercentage);
			discountFileAccess.update(moDis);
		}
		else if(discount instanceof DaySpecialDiscount) {
			DaySpecialDiscount dsDis = (DaySpecialDiscount)discount;
			dsDis.setDescription(description);
			dsDis.setDiscountPercentage(discountPercentage);
			dsDis.setStartDate(UssCommonUtil.convertStringToDate(startDate));
			dsDis.setDiscountDays(Integer.parseInt(discountDays));
			discountFileAccess.update(dsDis);
		}
	}
	
	public double getMembersTodaysHighestDiscount(Boolean isMember, Boolean isFirstPurchase) {
		double memberDiscount = getTodaysDiscount();
		if(isMember) {
			if(isFirstPurchase) {
				if(memberDiscount < getDiscountValueByCode(MEMBER_FIRST_DISCOUNT)) {
					memberDiscount = getDiscountValueByCode(MEMBER_FIRST_DISCOUNT);
				}
			}
			else {
				if(memberDiscount < getDiscountValueByCode(MEMBER_SUBSEQ_DISCOUNT)) {
					memberDiscount = getDiscountValueByCode(MEMBER_SUBSEQ_DISCOUNT);
				}
			}
		}
		return memberDiscount;
	}
	
	private double getDiscountValueByCode(String dicountcode) {
		return discountFileAccess.getDiscountByDiscountCode(dicountcode).getDiscountPercentage();
	}
		
	private double getTodaysDiscount() {
		double todaysHighestDiscount = 0.0;
		Date today = new Date();
		
		for(Discount discount : discountFileAccess.getAll()) {
			if(discount instanceof DaySpecialDiscount) {
				if (UssCommonUtil.isDateWithinRange(((DaySpecialDiscount) discount).getStartDate(), ((DaySpecialDiscount) discount).getEndDate(), today)) {
					if(todaysHighestDiscount < discount.getDiscountPercentage()) {
						todaysHighestDiscount = discount.getDiscountPercentage();
					}
				}
			}
		}
		return todaysHighestDiscount;
	}
}
