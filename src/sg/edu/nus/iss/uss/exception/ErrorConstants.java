package sg.edu.nus.iss.uss.exception;

public class ErrorConstants {
	
	public enum UssCode {
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
	
	
	/**
	 ************************** OTHER ****************************************
	 */
	
	

}
