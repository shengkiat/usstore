package sg.edu.nus.iss.uss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sg.edu.nus.iss.uss.exception.UssException;

import sg.edu.nus.iss.uss.exception.ErrorConstants;

public class UssCommonUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

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
	
    
    
}
