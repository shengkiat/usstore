package sg.edu.nus.iss.uss.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class DaySpecialDiscountTest {
	//Test fixtures
	DaySpecialDiscount discount1 = null;
	DaySpecialDiscount discount2 = null;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		discount1 = new DaySpecialDiscount("NATIONAL", "NATIONAL", 10.5, UssCommonUtil.convertStringToDate("2016-08-07"), 1);
		discount2 = new DaySpecialDiscount("CENTENARY", "CENTENARY",11, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}

	@After
	public void tearDown() throws Exception {
		discount1 = null;
		discount2 = null;
	}
	
	@Test
	public void testDaySpecialDiscount() {
		assertEquals("NATIONAL", discount1.getDiscountCode());
		assertEquals("NATIONAL", discount1.getDescription());
		assertEquals(10.5, discount1.getDiscountPercentage(),0);
		assertEquals("2016-08-07", UssCommonUtil.convertDateToString(discount1.getStartDate()));
		assertEquals(1, discount1.getDiscountDays());
		assertEquals("NATIONAL,NATIONAL,2016-08-07,1,10.5,A", discount1.toString());
		assertEquals("CENTENARY", discount2.getDiscountCode());
		assertEquals("CENTENARY", discount2.getDescription());
		assertEquals(11, discount2.getDiscountPercentage(),0);
		assertEquals("2016-02-01", UssCommonUtil.convertDateToString(discount2.getStartDate()));
		assertEquals(30, discount2.getDiscountDays());
		assertEquals("CENTENARY,CENTENARY,2016-02-01,30,11,A", discount2.toString());
	}
	
	@Test
	public void testWithInvalidDiscountCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_CODE);
		discount2 = new DaySpecialDiscount(null, "CENTENARY",11, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}
	
	@Test
	public void testWithEmptyDiscountCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_CODE);
		discount2 = new DaySpecialDiscount("", "CENTENARY",11, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}
	
	@Test
	public void testWithInvalidDiscountDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		discount2 = new DaySpecialDiscount("TEST", null, 11, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}
	
	@Test
	public void testWithEmptyDiscountDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		discount2 = new DaySpecialDiscount("TEST", "", 11, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}
	
	@Test
	public void testWithInvalidDiscountValue() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		discount2 = new DaySpecialDiscount("CENTENARY", "CENTENARY", 111, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}
	
	@Test
	public void testWithNegativeDiscountValue() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		discount2 = new DaySpecialDiscount("CENTENARY", "CENTENARY", -11, UssCommonUtil.convertStringToDate("2016-02-01"), 30);
	}
	
	@Test
	public void testWithInvalidDiscountDays() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DAYS);
		discount2 = new DaySpecialDiscount("CENTENARY", "CENTENARY", 11, UssCommonUtil.convertStringToDate("2016-02-01"), 0);
	}
	
	@Test
	public void testWithInvalidStartDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage("The date format must be yyyy-MM-dd");
		discount2 = new DaySpecialDiscount("CENTENARY", "CENTENARY", 11, UssCommonUtil.convertStringToDate("20160201"), 10);
	}

	@Test
	public void testToString() {
		assertEquals("NATIONAL,NATIONAL,2016-08-07,1,10.5,A", discount1.toString());
		assertEquals("CENTENARY,CENTENARY,2016-02-01,30,11,A", discount2.toString());
	}


	@Test
	public void testGetStartDate() {
		assertEquals("2016-08-07", UssCommonUtil.convertDateToString(discount1.getStartDate()));
		assertEquals("2016-02-01", UssCommonUtil.convertDateToString(discount2.getStartDate()));
	}

	@Test
	public void testSetStartDate() throws UssException {
		discount1.setStartDate(UssCommonUtil.convertStringToDate("2016-05-07"));
		assertEquals("2016-05-07", UssCommonUtil.convertDateToString(discount1.getStartDate()));
		
	}

	@Test
	public void testGetDiscountDays() {
		assertEquals(1, discount1.getDiscountDays());
		assertEquals(30, discount2.getDiscountDays());
	}

	@Test
	public void testSetDiscountDays() throws UssException {
		discount2.setDiscountDays(90);
		assertEquals(90, discount2.getDiscountDays());
	}
	
	@Test
	public void testSetInvalidDiscountDays() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DAYS);
		discount2.setDiscountDays(0);
	}

	@Test
	public void testGetEndDate() {
		assertEquals("2016-08-07", UssCommonUtil.convertDateToString(discount1.getEndDate()));
		assertEquals("2016-03-01", UssCommonUtil.convertDateToString(discount2.getEndDate()));
	}

	@Test
	public void testGetDiscountCode() {
		assertEquals("NATIONAL", discount1.getDiscountCode());
		assertEquals("CENTENARY", discount2.getDiscountCode());
	}

	@Test
	public void testGetDescription() {
		assertEquals("NATIONAL", discount1.getDescription());
		assertEquals("CENTENARY", discount2.getDescription());
	}

	@Test
	public void testSetDescription() throws UssException {
		discount2.setDescription("XYZ");
		assertEquals("XYZ", discount2.getDescription());
	}
	
	@Test
	public void testSetNullDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		discount2.setDescription(null);
	}
	
	@Test
	public void testSetEmptyDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		discount2.setDescription("");
	}

	@Test
	public void testGetDiscountPercentage() {
		assertEquals(10.5, discount1.getDiscountPercentage(),0);
		assertEquals(11, discount2.getDiscountPercentage(),0);
	}

	@Test
	public void testSetDiscountPercentage() throws UssException {
		discount1.setDiscountPercentage(20.0);
		assertEquals(20, discount1.getDiscountPercentage(),0);
	}
	
	@Test
	public void testSetInvalidDiscountPercentage() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		discount1.setDiscountPercentage(-20.0);
	}
}
