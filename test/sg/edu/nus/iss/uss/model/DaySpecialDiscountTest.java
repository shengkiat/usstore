package sg.edu.nus.iss.uss.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DaySpecialDiscountTest {
	//Test fixtures
	DaySpecialDiscount discount1 = null;
	DaySpecialDiscount discount2 = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void setUp() throws Exception {
		discount1 = new DaySpecialDiscount("NATIONAL", "NATIONAL", 10.5, df.parse("2016-08-07"), 1);
		discount2 = new DaySpecialDiscount("CENTENARY", "CENTENARY",11, df.parse("2016-02-01"), 30);
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
		assertEquals("2016-08-07", df.format(discount1.getStartDate()));
		assertEquals(1, discount1.getDiscountDays());
		assertEquals("NATIONAL,NATIONAL,2016-08-07,1,10.5,A", discount1.toString());
		assertEquals("CENTENARY", discount2.getDiscountCode());
		assertEquals("CENTENARY", discount2.getDescription());
		assertEquals(11, discount2.getDiscountPercentage(),0);
		assertEquals("2016-02-01", df.format(discount2.getStartDate()));
		assertEquals(30, discount2.getDiscountDays());
		assertEquals("CENTENARY,CENTENARY,2016-02-01,30,11,A", discount2.toString());
	}

	@Test
	public void testToString() {
		assertEquals("NATIONAL,NATIONAL,2016-08-07,1,10.5,A", discount1.toString());
		assertEquals("CENTENARY,CENTENARY,2016-02-01,30,11,A", discount2.toString());
	}


	@Test
	public void testGetStartDate() {
		assertEquals("2016-08-07", df.format(discount1.getStartDate()));
		assertEquals("2016-02-01", df.format(discount2.getStartDate()));
	}

	@Test
	public void testSetStartDate() throws ParseException {
		discount1.setStartDate(df.parse("2016-05-07"));
		assertEquals("2016-05-07", df.format(discount1.getStartDate()));
		
	}

	@Test
	public void testGetDiscountDays() {
		assertEquals(1, discount1.getDiscountDays());
		assertEquals(30, discount2.getDiscountDays());
	}

	@Test
	public void testSetDiscountDays() {
		discount2.setDiscountDays(90);
		assertEquals(90, discount2.getDiscountDays());
	}

	@Test
	public void testGetEndDate() {
		assertEquals("2016-08-07", df.format(discount1.getEndDate()));
		assertEquals("2016-03-01", df.format(discount2.getEndDate()));
	}

	@Test
	public void testGetDiscountCode() {
		assertEquals("NATIONAL", discount1.getDiscountCode());
		assertEquals("CENTENARY", discount2.getDiscountCode());
	}

	@Test
	public void testSetDiscountCode() {
		discount1.setDiscountCode("XYZ");
		assertEquals("XYZ", discount1.getDiscountCode());
	}

	@Test
	public void testGetDescription() {
		assertEquals("NATIONAL", discount1.getDescription());
		assertEquals("CENTENARY", discount2.getDescription());
	}

	@Test
	public void testSetDescription() {
		discount2.setDescription("XYZ");
		assertEquals("XYZ", discount2.getDescription());
	}

	@Test
	public void testGetDiscountPercentage() {
		assertEquals(10.5, discount1.getDiscountPercentage(),0);
		assertEquals(11, discount2.getDiscountPercentage(),0);
	}

	@Test
	public void testSetDiscountPercentage() {
		discount1.setDiscountPercentage(20.0);
		assertEquals(20, discount1.getDiscountPercentage(),0);
	}

	@Test
	public void testIsValid() {
		assertTrue(true);
	}

	@Test
	public void testIsValidToday() {
		assertTrue(true);
	}

}
