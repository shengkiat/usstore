package sg.edu.nus.iss.uss.util;

import java.util.Calendar;

import org.junit.Test;

import static org.junit.Assert.*;

public class UssCommonUtilTest {
	
	@Test
	public void testConvertDateToString() {
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, 2016);
	    cal.set(Calendar.MONTH, 2);
	    cal.set(Calendar.DAY_OF_MONTH, 13);
	   
		assertEquals("2016-03-13", UssCommonUtil.convertDateToString(cal.getTime()));
	}
	
	@Test
	public void testConvertStringToDate() {
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, 2016);
	    cal.set(Calendar.MONTH, 2);
	    cal.set(Calendar.DAY_OF_MONTH, 13);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);

		assertEquals(cal.getTime(), UssCommonUtil.convertStringToDate("2016-03-13"));
	}

}
