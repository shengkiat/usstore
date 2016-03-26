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
     ************************** CHECKOUT ****************************************
     */

    public static final String PAYMENT_VALIDATION_FAIL = "Payment Validation Fail";
    public static final String AMOUNT_RECEIVED_LESS_THAN_TOTAL_PAYABLE = "Amount Received Less Than Amount Payable";
	
	/**
	 * ************************ PRODUCT *****************************************
	 */
	public static final String PRODUCT_EXISTS = "Product Exists!";
	public static final String PRODUCT_NOT_EXISTS = "Product Does Not Exists!";
	public static final String PRODUCTFILE_EMPTY = "Product List is Empty!";
	
	
	/**
	 ************************** CATEGORY ****************************************
	 */
	public static final String CATEGORY_EXISTS = "Category Exists!";
	public static final String CATEGORY_NOT_EXISTS = "Category Does Not Exists!";
	public static final String CATEGORYFILE_EMPTY = "Category File is Empty!";
	
	/**
	 ************************** MEMBER ****************************************
	 */
	public static final String MEMBER_NOT_EXISTS = "Member does not exist!";
	public static final String NOT_ENOUGH_POINTS = "Not enough points!";

	
	
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
	public static final String SYSTEM_ERROR = "System Error, please contact adminstrator.";
	
	
	/**
	 ************************** DAO ****************************************
	 */
	public static final String EMPTY_CONTENT = "All arr content cannot be null or empty";
	
	/**
	 ************************** VALIDATION ****************************************
	 */
	public static final String REQUIRED_START_END_DATE = "Please enter Start Date and End Date";
}
