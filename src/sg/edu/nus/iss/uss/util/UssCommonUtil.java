package sg.edu.nus.iss.uss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UssCommonUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
	public static String convertDateToString(Date date){
		return DEFAULT_DATE_FORMATTER.format(date);
	}
	
	public static Date convertStringToDate(String date) {
		try {
			return DEFAULT_DATE_FORMATTER.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
