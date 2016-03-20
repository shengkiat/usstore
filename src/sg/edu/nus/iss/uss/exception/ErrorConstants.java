package sg.edu.nus.iss.uss.exception;

public class ErrorConstants {
	
	public enum UssCode {
		  DAO(99),
		  VALIDATION(100),
		  AUTHORIZATION(101),
		  CATEGORY(102),
		  CHECKOUT(103),
		  DISCOUNT(104),
		  MEMBER(105),
		  PRODUCT(106),
		  TRANSACTION(107),
		  VENDOR(108),
		  OTHER(109);
		
		private final int number;
		 
	    private UssCode(int number) {
	      this.number = number;
	    }
	 
	    public int getNumber() {
	    return number;
	  }
	}
	
	
	
	/**
	 ************************** CATEGORY ****************************************
	 */
	
	
	/**
	 ************************** MEMBER ****************************************
	 */
	public static final String MEMBER_NOT_EXISTS = "Member does not exist!";
	public static final String NOT_ENOUGH_POINTS = "Not enough points!";
	public static final String CATEGORY_EXISTS = "Category Exists!";
	
	
	/**
	 ************************** DISCOUNT ****************************************
	 */
	public static final String INVALID_DISCOUNT_CODE = "Invalid discount code!";
	public static final String INVALID_DISCOUNT_DESCRIPTION = "Invalid discount description!";
	public static final String INVALID_DISCOUNT_PERCENTAGE = "Invalid discount percentage!";
	public static final String INVALID_DISCOUNT_DAYS = "Minimum 1 discount day allowed!";
	public static final String INVALID_DISCOUNT_STARTDATE = "past date not allowed!";	
	public static final String INVALID_DISCOUNT_RECORDS = "Less than 5 discounts not allowed!";
	public static final String DISCOUNT_EXISTS = "Discount already exists!";
	public static final String DISCOUNT_NOT_EXIST = "Discount does not exist!";
	/**
	 ************************** OTHER ****************************************
	 */
	
	/**
	 ************************** DAO ****************************************
	 */
	public static final String EMPTY_CONTENT = "All arr content cannot be null or empty";
	
	/**
	 ************************** VALIDATION ****************************************
	 */

}
