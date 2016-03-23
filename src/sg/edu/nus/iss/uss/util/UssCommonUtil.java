package sg.edu.nus.iss.uss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Product;

public class UssCommonUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
	
	public static HashMap<Category,ArrayList<Product>> catPrdHMap ;
	
	public static SimpleDateFormat getDefaultDateFormatter() {
		return DEFAULT_DATE_FORMATTER;
	}
	
	public static String convertDateToString(Date date){
		return DEFAULT_DATE_FORMATTER.format(date);
	}
	
	public static Date convertStringToDate(String date) throws UssException {
		
		if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new UssException(ErrorConstants.UssCode.VALIDATION, "The date format must be " + DATE_FORMAT);
		}
		
		try {
			return DEFAULT_DATE_FORMATTER.parse(date);
		} catch (ParseException e) {
			throw new UssException(ErrorConstants.UssCode.VALIDATION, "The date format must be " + DATE_FORMAT);
		}
	}
	
    public static boolean isDateLeftGreaterThanRight(java.util.Date dateLeft, java.util.Date dateRight) {
        return dateLeft.compareTo(dateRight) > 0;
    }
    
    public static boolean isDateWithinRange(java.util.Date startDate, java.util.Date endDate, java.util.Date toComparedDate) {
        return startDate.compareTo(toComparedDate) <= 0 && toComparedDate.compareTo(endDate) <= 0;
    }
	
    public int compare(String str1, String str2) {

        // extract numeric portion out of the string and convert them to int
        // and compare them, roughly something like this

        int num1 = Integer.parseInt(str1.substring(0, str1.indexOf("%") - 1));
        int num2 = Integer.parseInt(str2.substring(0, str2.indexOf("%") - 1));

        return num1 - num2;

     }
    
    public void Clear_HM() {
    	catPrdHMap.clear();
    	}
    }
    
    
}
