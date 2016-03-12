package sg.edu.nus.iss.uss.model;

import java.util.Date;

import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class Discount {
	
	private String discountCode;
	private String description;
	private Date startDate;
	private int discountDays;//Period of discount in days (where applicable or “ALWAYS”)
	private int discountPercentage;//Percentage discount without the percentage symbol e.g. “10” for 10% discount
	private String customerType;//Applicable to Member (M) or All (A)
	
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setDiscountDays(int discountDays) {
		this.discountDays = discountDays;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public boolean isValid(){
		
		return true;//TODO
	}
	
	public boolean isValidToday(){
		
		return true;//TODO
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
		sb.append(",");
		
		if(null != startDate){
			String day = UssCommonUtil.convertDateToString(startDate);
			if(null != day){
				sb.append(day);
			}
		}
		sb.append(",");
		
		//TODO append info for other attributes
		
        
        return sb.toString();
	}
}


