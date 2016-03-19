package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.DiscountDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class DiscountService extends UssCommonService {
	private static final String MEMBER_FIRST_DISCOUNT = "MEMBER_FIRST";
	private static final String MEMBER_SUBSEQ_DISCOUNT = "MEMBER_SUBSEQ";
	
	private List<Discount> availableDiscountList;
	private DiscountDataAccess discountFileAccess;
	
	public DiscountService(DiscountDataAccess discountFileAccess) {
		this.discountFileAccess = discountFileAccess;
		availableDiscountList = new ArrayList<Discount>();
		availableDiscountList = discountFileAccess.getAll();
	}
	
	public List<Discount> getAll() {
		return availableDiscountList;
	}
	
	public void AddNewDiscount(String discountCode, String description, double discountPercentage, Date startDate, int discountDays) throws UssException {
		availableDiscountList.add(new DaySpecialDiscount(discountCode, description, discountPercentage, startDate, discountDays));
		discountFileAccess.create(availableDiscountList);
	}
	
	public void UpdateDiscount(String discountCode, String description, double discountPercentage, Date startDate, int discountDays) throws UssException {
		int index = -1;
		for(Discount temp : availableDiscountList) {
			if(temp.getDiscountCode() == discountCode) {
				index = availableDiscountList.indexOf(temp);
				if(temp instanceof MemberOnlyDiscount) {
					MemberOnlyDiscount moDis = (MemberOnlyDiscount)temp;
					moDis.setDescription(description);
					moDis.setDiscountPercentage(discountPercentage);
					availableDiscountList.set(index, moDis);
				}
				else if(temp instanceof DaySpecialDiscount) {
					DaySpecialDiscount tempDis = (DaySpecialDiscount)temp;
					tempDis.setDescription(description);
					tempDis.setDiscountPercentage(discountPercentage);
					tempDis.setStartDate(startDate);
					tempDis.setDiscountDays(discountDays);
					availableDiscountList.set(index, tempDis);
				}
			}
		}
		discountFileAccess.create(availableDiscountList);
	}
	
	public void RemoveDiscount(String discountCode) {
		for(Discount temp : availableDiscountList) {
			if(temp.getDiscountCode() == discountCode) {
					availableDiscountList.remove(temp);
			}
		}
	}
	
	public double getMembersTodaysHighestDiscount(Boolean isMember, Boolean isFirstPurchase) {
		double memberDiscount = getTodaysDiscount();
		if(isMember) {
			if(isFirstPurchase) {
				if(memberDiscount < getMemberFirstDiscount()) {
					memberDiscount = getMemberFirstDiscount();
				}
			}
			else {
				if(memberDiscount < getMemberSubseqDiscount()) {
					memberDiscount = getMemberSubseqDiscount();
				}
			}
		}
		return memberDiscount;
	}
	
	private double getMemberFirstDiscount() {
		for(Discount temp : availableDiscountList) {
			if(temp.getDiscountCode().equals(MEMBER_FIRST_DISCOUNT)) {
				return temp.getDiscountPercentage();
			}
		}
		return 0;
	}
	
	private double getMemberSubseqDiscount() {
		for(Discount temp : availableDiscountList) {
			if(temp.getDiscountCode().equals(MEMBER_SUBSEQ_DISCOUNT)) {
				return temp.getDiscountPercentage();
			}
		}
		return 0;
	}
		
	public double getTodaysDiscount() {
		double todaysHighestDiscount = 0.0;
		Date today = new Date();
		
		for(Discount discount : availableDiscountList) {
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
