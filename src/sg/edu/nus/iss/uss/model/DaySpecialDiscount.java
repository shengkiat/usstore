package sg.edu.nus.iss.uss.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class DaySpecialDiscount extends Discount{
	private Date startDate;
	private int discountDays;//Period of discount in days (where applicable or “ALWAYS”)

	public DaySpecialDiscount(String discountCode, String description, double discountPercentage, Date startDate, int discountDays) throws UssException {
		super(discountCode, description, discountPercentage);
		if(discountDays <= 0)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_DAYS);
		this.startDate = startDate;
		this.discountDays = discountDays;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDiscountDays() {
		return discountDays;
	}
	public void setDiscountDays(int discountDays) throws UssException {
		if(discountDays <= 0)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_DAYS);
		this.discountDays = discountDays;
	}
	
	public Date getEndDate()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());
		cal.add(Calendar.DATE, getDiscountDays()-1);
		return cal.getTime();
	}
	
	@Override
	public String toString() {//MEMBER_FIRST,First purchase by member,2016-02-01,30,20,A
		String discountString = (new BigDecimal(Double.toString(getDiscountPercentage()))).stripTrailingZeros().toPlainString();
		return (super.toString() + "," + UssCommonUtil.convertDateToString(getStartDate()) + "," + getDiscountDays() + "," + discountString + ",A");
	}
}
