package sg.edu.nus.iss.uss.model;

import java.math.BigDecimal;

import sg.edu.nus.iss.uss.exception.UssException;

public class MemberOnlyDiscount extends Discount{

	public MemberOnlyDiscount(String discountCode, String description, double discountPercentage) throws UssException {
		super(discountCode, description, discountPercentage);
	}
	@Override
	public String toString() {//MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,20,M
		String discountString = (new BigDecimal(Double.toString(getDiscountPercentage()))).stripTrailingZeros().toPlainString();
		return (super.toString() + ",ALWAYS,ALWAYS," + discountString + ",M");
	}
}
