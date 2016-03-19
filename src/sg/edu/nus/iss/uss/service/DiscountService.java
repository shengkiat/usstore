package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.DiscountDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
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
		ValidateIncomingPrameters(discountCode, description, discountPercentage, startDate, discountDays);
		if(DiscountExists(discountCode))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.DISCOUNT_EXISTS);
		DaySpecialDiscount temp = new DaySpecialDiscount(discountCode, description, discountPercentage, startDate, discountDays);
		availableDiscountList.add(temp);
		discountFileAccess.create(temp);
	}
	
	public void UpdateDiscount(String discountCode, String description, double discountPercentage, Date startDate, int discountDays) throws UssException {
		ValidateIncomingPrameters(discountCode, description, discountPercentage, startDate, discountDays);
		if(DiscountExists(discountCode))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.DISCOUNT_NOT_EXIST);
		int index = -1;
		for(Discount temp : availableDiscountList) {
			if(temp.getDiscountCode() == discountCode) {
				index = availableDiscountList.indexOf(temp);
				if(temp instanceof MemberOnlyDiscount) {
					MemberOnlyDiscount moDis = (MemberOnlyDiscount)temp;
					moDis.setDescription(description);
					moDis.setDiscountPercentage(discountPercentage);
					availableDiscountList.set(index, moDis);
					discountFileAccess.update(moDis);
				}
				else if(temp instanceof DaySpecialDiscount) {
					DaySpecialDiscount dsDis = (DaySpecialDiscount)temp;
					dsDis.setDescription(description);
					dsDis.setDiscountPercentage(discountPercentage);
					dsDis.setStartDate(startDate);
					dsDis.setDiscountDays(discountDays);
					availableDiscountList.set(index, dsDis);
					discountFileAccess.update(dsDis);
				}
			}
		}
	}
	
	public void RemoveDiscount(String discountCode) throws UssException {
		if(DiscountExists(discountCode))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.DISCOUNT_NOT_EXIST);
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
		
	private double getTodaysDiscount() {
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
	
	private void ValidateIncomingPrameters(String discountCode, String description, double discountPercentage, Date startDate, int discountDays) throws UssException {
		if(discountCode == null)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_CODE);
		if(description == null)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		if(discountPercentage < 0 || discountPercentage > 100)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		if(UssCommonUtil.isDateLeftGreaterThanRight(new Date(), startDate))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_STARTDATE);
		if(discountDays <= 0)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_DAYS);
	}
	
	private boolean DiscountExists(String discountCode) {
		for(Discount temp : availableDiscountList) {
			if(temp.getDiscountCode() == discountCode)
				return true;
		}
		return false;
	}
}
