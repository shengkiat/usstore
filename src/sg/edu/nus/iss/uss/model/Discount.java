package sg.edu.nus.iss.uss.model;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;

public abstract class Discount {
	
	private String discountCode;
	private String description;
	private double discountPercentage;//Percentage discount without the percentage symbol e.g. "10" for 10% discount
	
	public Discount(String discountCode, String description, double discountPercentage) throws UssException {
		if(discountCode == null || discountCode.equals(""))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_CODE);
		if(discountPercentage < 0 || discountPercentage > 100)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		if(description == null || description.equals(""))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) throws UssException {
		if(discountPercentage < 0 || discountPercentage > 100)
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		this.discountPercentage = discountPercentage;
	}	
	
	@Override
	public String toString() {//MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,20,M
		
		StringBuffer sb = new StringBuffer();
		if(null != discountCode){
			sb.append(discountCode);
		}
		sb.append(",");
		
		if(null != description){
			sb.append(description);
		}
		return sb.toString();
	}
}


