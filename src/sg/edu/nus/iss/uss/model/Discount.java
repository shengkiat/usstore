package sg.edu.nus.iss.uss.model;

public abstract class Discount {
	
	private String discountCode;
	private String description;
	private double discountPercentage;//Percentage discount without the percentage symbol e.g. “10” for 10% discount
	
	public Discount(String discountCode, String description, double discountPercentage) {
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
	}
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
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	public boolean isValid() {
		
		return true;//TODO
	}
	
	public boolean isValidToday() {
		
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
		return sb.toString();
	}
}


