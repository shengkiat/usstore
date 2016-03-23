package sg.edu.nus.iss.uss.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;

public class MemberOnlyDiscountTest {
	//Test fixtures
	MemberOnlyDiscount memDiscount1 = null;
	MemberOnlyDiscount memDiscount2 = null;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		memDiscount1 = new MemberOnlyDiscount("MEMBER_FIRST", "First purchase by member", 10.5);
		memDiscount2 = new MemberOnlyDiscount("MEMBER_SUBSEQ", "Subsequent purchase by member", 12.5);
	}

	@After
	public void tearDown() throws Exception {
		memDiscount1 = null;
		memDiscount2 = null;
	}
	
	@Test
	public void testMemberOnlyDiscount() {
		assertEquals("MEMBER_FIRST", memDiscount1.getDiscountCode());
		assertEquals("First purchase by member", memDiscount1.getDescription());
		assertEquals(10.5, memDiscount1.getDiscountPercentage(),0);
		assertEquals("MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,10.5,M", memDiscount1.toString());
		assertEquals("MEMBER_SUBSEQ", memDiscount2.getDiscountCode());
		assertEquals("Subsequent purchase by member", memDiscount2.getDescription());
		assertEquals(12.5, memDiscount2.getDiscountPercentage(),0);
		assertEquals("MEMBER_SUBSEQ,Subsequent purchase by member,ALWAYS,ALWAYS,12.5,M", memDiscount2.toString());
	}
	
	@Test
	public void testMemberOnlyDiscountWithInvalidCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_CODE);
		memDiscount2 = new MemberOnlyDiscount(null, "Subsequent purchase by member", 12.5);
	}
	
	@Test
	public void testMemberOnlyDiscountWithInvalidPercentaage() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		memDiscount2 = new MemberOnlyDiscount("TEST", "Subsequent purchase by member", 112.5);
	}

	@Test
	public void testToString() {
		assertEquals("MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,10.5,M", memDiscount1.toString());
		assertEquals("MEMBER_SUBSEQ,Subsequent purchase by member,ALWAYS,ALWAYS,12.5,M", memDiscount2.toString());
	}

	@Test
	public void testGetDiscountCode() {
		assertEquals("MEMBER_FIRST", memDiscount1.getDiscountCode());
		assertEquals("MEMBER_SUBSEQ", memDiscount2.getDiscountCode());
	}

	@Test
	public void testSetDiscountCode() {
		memDiscount1.setDiscountCode("CODE");
		assertEquals("CODE", memDiscount1.getDiscountCode());
		memDiscount1.setDiscountCode("MEMBER_FIRST");
		assertEquals("MEMBER_FIRST", memDiscount1.getDiscountCode());
	}

	@Test
	public void testGetDescription() {
		assertEquals("First purchase by member", memDiscount1.getDescription());
		assertEquals("Subsequent purchase by member", memDiscount2.getDescription());
	}

	@Test
	public void testSetDescription() {
		memDiscount2.setDescription("Description");
		assertEquals("Description", memDiscount2.getDescription());
		memDiscount2.setDescription("Subsequent purchase by member");
		assertEquals("Subsequent purchase by member", memDiscount2.getDescription());
	}

	@Test
	public void testGetDiscountPercentage() {
		assertEquals(10.5, memDiscount1.getDiscountPercentage(),0);
		assertEquals(12.5, memDiscount2.getDiscountPercentage(),0);
	}

	@Test
	public void testSetDiscountPercentage() throws UssException {
		memDiscount1.setDiscountPercentage(10.20);
		assertEquals(10.2, memDiscount1.getDiscountPercentage(),0);
		assertEquals("MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,10.2,M", memDiscount1.toString());
	}
	
	@Test
	public void testSetInvalidDiscountPercentage() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		memDiscount1.setDiscountPercentage(110.20);
	}

}
