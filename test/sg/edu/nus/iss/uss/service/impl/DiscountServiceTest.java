package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sg.edu.nus.iss.uss.dao.filedataaccess.DiscountFileDataAccess;
import sg.edu.nus.iss.uss.service.impl.DiscountService;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.util.TestUtil;
import sg.edu.nus.iss.uss.util.UssCommonUtil;


public class DiscountServiceTest {
	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Discounts.dat";
	DiscountService dicountService = null;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		Path path = getTestPath();

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("Test data file already exists: " + e.getMessage());
        }
		
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(TEST_DATA_DIR + File.separator + TEST_FILE_NAME), "utf-8"))) {
			writer.write("MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,20,M");
			writer.newLine();
			writer.write("MEMBER_SUBSEQ,Subsequent purchase by member,ALWAYS,ALWAYS,25,M");
			writer.newLine();
			writer.write("CENTENARY,Centenary Celebration in 2016,2016-01-01,365,15,A");
			writer.newLine();
			writer.write("PRESIDENT_BDAY,University President's birthday,2016-01-01,60,20,A");
			writer.newLine();
			writer.write("ORIENTATION_DAY,Orientation Day,2016-02-02,3,50,A");
			writer.newLine();
		} 
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		dicountService = new DiscountService(new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR));
	}

	@After
	public void tearDown() throws Exception {
		dicountService = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}

	@Test
	public void testGetAll() {
		assertEquals(5, dicountService.getAll().size());
	}
	
	@Test
	public void testAddNewDiscountAndGetAll() throws UssException {
		dicountService.addNewDiscount("SG50", "SG50 2016", 50, UssCommonUtil.convertStringToDate("2016-08-09"),7);
		dicountService.addNewDiscount("NATIONAL_DAY", "National Day Celebration in 2016", 25, UssCommonUtil.convertStringToDate("2016-08-09"),1);
		assertEquals(7, dicountService.getAll().size());
	}
	
	@Test
	public void testAddNewInvalidDiscountWithNullCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_CODE);
		dicountService.addNewDiscount(null, "SG50 2016", 50, UssCommonUtil.convertStringToDate("2016-08-09"),7);
	}
	
	@Test
	public void testAddNewInvalidDiscountWithEmptyCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_CODE);
		dicountService.addNewDiscount("", "SG50 2016", 50, UssCommonUtil.convertStringToDate("2016-08-09"),7);
	}
	
	@Test
	public void testAddNewInvalidDiscountWithNullDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		dicountService.addNewDiscount("SG50", null, 50, UssCommonUtil.convertStringToDate("2016-08-09"),7);
	}
	
	@Test
	public void testAddNewInvalidDiscountWithEmptyDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		dicountService.addNewDiscount("SG50", null, 50, UssCommonUtil.convertStringToDate("2016-08-09"),7);
	}
	
	@Test
	public void testAddNewInvalidDiscountWithInvalidDiscountPercentage() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		dicountService.addNewDiscount("SG50", "SG50 2016", 150, UssCommonUtil.convertStringToDate("2016-08-09"),7);
	}
	
	@Test
	public void testAddNewInvalidDiscountWithInvalidDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage("The date format must be yyyy-MM-dd");
		dicountService.addNewDiscount("SG50", "SG50 2016", 50, UssCommonUtil.convertStringToDate("20160809"),7);
	}
	
	@Test
	public void testAddNewInvalidDiscountWithInvalidDays() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DAYS);
		dicountService.addNewDiscount("SG50", "SG50 2016", 50, UssCommonUtil.convertStringToDate("2016-08-09"),0);
	}
	
	@Test
	public void testUpdateDiscount() throws UssException {
		dicountService.updateDiscount("ORIENTATION_DAY", "National Day Celebration in 2014", 25, "2016-08-09","1");
		assertEquals(5, dicountService.getAll().size());
		Discount test = null;
		test = dicountService.getDiscountByCode("ORIENTATION_DAY");
		assertNotNull(test);
		assertTrue(test instanceof DaySpecialDiscount);
		DaySpecialDiscount dsDiscount = (DaySpecialDiscount)test;
		assertEquals("ORIENTATION_DAY", dsDiscount.getDiscountCode());
		assertEquals("National Day Celebration in 2014", dsDiscount.getDescription());
		assertEquals(25, dsDiscount.getDiscountPercentage(), 0);
		assertEquals("2016-08-09",UssCommonUtil.convertDateToString(dsDiscount.getStartDate()));
		assertEquals(1,dsDiscount.getDiscountDays());
	}
	
	@Test
	public void testUpdateDiscountWithNullCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.DISCOUNT_NOT_EXIST);
		dicountService.updateDiscount(null, "National Day Celebration in 2014", 25, "2016-08-09","1");
	}
	
	@Test
	public void testUpdateDiscountWithEmptyCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.DISCOUNT_NOT_EXIST);
		dicountService.updateDiscount("", "National Day Celebration in 2014", 25, "2016-08-09","1");
	}
	
	@Test
	public void testUpdateDiscountWithNonExistingCode() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.DISCOUNT_NOT_EXIST);
		dicountService.updateDiscount("XYZ", "", 25, "2016-08-09","1");
	}
	
	@Test
	public void testUpdateDiscountWithNullDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		dicountService.updateDiscount("ORIENTATION_DAY", null, 25, "2016-08-09","1");
	}
	
	@Test
	public void testUpdateDiscountWithEmptyDescription() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DESCRIPTION);
		dicountService.updateDiscount("ORIENTATION_DAY", "", 25, "2016-08-09","1");
	}
	
	@Test
	public void testUpdateDiscountWithInvalidPercentage() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_PERCENTAGE);
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 125, "2016-08-09","1");
	}
	
	@Test
	public void testUpdateDiscountWithInvalidDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage("The date format must be yyyy-MM-dd");
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 25, "XYZ","1");
	}
	
	@Test
	public void testUpdateDiscountWithNullDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage("The date format must be yyyy-MM-dd");
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 25, null,"1");
	}
	
	@Test
	public void testUpdateDiscountWithEmptyDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage("The date format must be yyyy-MM-dd");
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 25, "","1");
	}
	
	@Test
	public void testUpdateDiscountWithInvalidDays() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DAYS);
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 25, "2016-08-09","0");
	}
	
	@Test
	public void testUpdateDiscountWithEmptyDays() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DAYS);
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 25, "2016-08-09","");
	}
	
	@Test
	public void testUpdateDiscountWithNullDays() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_DAYS);
		dicountService.updateDiscount("ORIENTATION_DAY", "ORIENTATION_DAY", 25, "2016-08-09",null);
	}
	
	@Test
	public void testValidgetDiscountByCode() throws UssException {
		Discount test = null;
		test = dicountService.getDiscountByCode("MEMBER_SUBSEQ");
		assertNotNull(test);
		assertTrue(test instanceof MemberOnlyDiscount);
		MemberOnlyDiscount mDiscount = (MemberOnlyDiscount)test;
		assertEquals("MEMBER_SUBSEQ", mDiscount.getDiscountCode());
		assertEquals("Subsequent purchase by member", mDiscount.getDescription());
		assertEquals(25, mDiscount.getDiscountPercentage(), 0);

		test = dicountService.getDiscountByCode("ORIENTATION_DAY");
		assertNotNull(test);
		assertTrue(test instanceof DaySpecialDiscount);
		DaySpecialDiscount dsDiscount = (DaySpecialDiscount)test;
		assertEquals("ORIENTATION_DAY", dsDiscount.getDiscountCode());
		assertEquals("Orientation Day", dsDiscount.getDescription());
		assertEquals(50, dsDiscount.getDiscountPercentage(), 0);
		assertEquals("2016-02-02",UssCommonUtil.convertDateToString(dsDiscount.getStartDate()));
		assertEquals(3,dsDiscount.getDiscountDays());
	}
	
	public void testGetDiscountByCodeWithInvalidCode() throws UssException, IOException {
		assertNull(dicountService.getDiscountByCode("MEMBERFIRST"));
	}
	
	public void testGetDiscountByCodeWithEmptyCode() throws UssException, IOException {
		assertNull(dicountService.getDiscountByCode(""));
	}
	
	@Test
	public void testGetMembersTodaysHighestDiscount() {
		assertEquals(20,dicountService.getMembersTodaysHighestDiscount(true, true),0);
		assertEquals(25,dicountService.getMembersTodaysHighestDiscount(true, false),0);
		assertEquals(15,dicountService.getMembersTodaysHighestDiscount(false, false),0);
	}
}
