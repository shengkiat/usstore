package sg.edu.nus.iss.uss.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import sg.edu.nus.iss.uss.exception.UssException;

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
	public void testConvertStringToDate() throws UssException {
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
	
	@Test
	public void testIsDateLeftGreaterThanRightShouldReturnTrue() throws UssException {
		Date dateLeft = UssCommonUtil.convertStringToDate("2016-03-13");
		Date dateRight = UssCommonUtil.convertStringToDate("2016-03-12");
	   
		assertTrue(UssCommonUtil.isDateLeftGreaterThanRight(dateLeft, dateRight));
	}
	
	@Test
	public void testIsDateLeftGreaterThanRightShouldReturnFalse() throws UssException {
		Date dateRight = UssCommonUtil.convertStringToDate("2016-03-13");
		Date dateLeft = UssCommonUtil.convertStringToDate("2016-03-12");
	   
		assertFalse(UssCommonUtil.isDateLeftGreaterThanRight(dateLeft, dateRight));
	}
	
	@Test
	public void testIsDateWithinRangeShouldReturnFalse() throws UssException {
		Date startDate = UssCommonUtil.convertStringToDate("2016-03-13");
		Date endDate = UssCommonUtil.convertStringToDate("2016-03-14");
		
		Date toCompare = UssCommonUtil.convertStringToDate("2016-03-12");
	   
		assertFalse(UssCommonUtil.isDateWithinRange(startDate, endDate, toCompare));
	}
	
	@Test
	public void testIsDateWithinRangeShouldReturnTrue() throws UssException {
		Date startDate = UssCommonUtil.convertStringToDate("2016-03-13");
		Date endDate = UssCommonUtil.convertStringToDate("2016-03-14");
		
		Date toCompare = UssCommonUtil.convertStringToDate("2016-03-13");
	   
		assertTrue(UssCommonUtil.isDateWithinRange(startDate, endDate, toCompare));
	}
}
