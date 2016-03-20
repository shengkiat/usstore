package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
	public void testAddNewDiscountWithInvalidStartDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_STARTDATE);
		dicountService.addNewDiscount(null, "SG50 2016", 50, UssCommonUtil.convertStringToDate("2015-08-09"),7);
	}
	
	@Test
	public void testEditDiscount() throws UssException {
		dicountService.updateDiscount("ORIENTATION_DAY", "National Day Celebration in 2014", 25, UssCommonUtil.convertStringToDate("2016-08-09"),1);
		assertEquals(5, dicountService.getAll().size());
	}
	
	@Test
	public void testEditDiscountWithInvalidStartDate() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_STARTDATE);
		dicountService.updateDiscount("ORIENTATION_DAY", "National Day Celebration in 2014", 25, UssCommonUtil.convertStringToDate("2015-08-09"),1);
	}
	
	@Test
	public void testGetMembersTodaysHighestDiscount() {
		assertEquals(20,dicountService.getMembersTodaysHighestDiscount(true, true),0);
		assertEquals(25,dicountService.getMembersTodaysHighestDiscount(true, false),0);
		assertEquals(15,dicountService.getMembersTodaysHighestDiscount(false, false),0);
	}

}
