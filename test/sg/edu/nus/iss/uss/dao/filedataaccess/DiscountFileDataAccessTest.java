package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sg.edu.nus.iss.uss.dao.IDiscountDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.util.TestUtil;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class DiscountFileDataAccessTest {
	
	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Discounts.dat";
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private IDiscountDataAccess testDiscountDataAccess;

	@Before
	public void setUp() throws Exception {
		Path path = getTestPath();

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("Test data file already exists: " + e.getMessage());
        }
	}

	@After
	public void tearDown() throws Exception {
		testDiscountDataAccess = null;
		
		TestUtil.destoryFile(TestUtil.getTestPath(TEST_FILE_NAME));
	}
	
	private Path getTestPath() {
		return Paths.get(TestUtil.getTestPath(TEST_FILE_NAME));
	}
	
	private void creteFileWithDiscountData() throws IOException {
		TestUtil.createFileWithLines(TestUtil.getTestPath(TEST_FILE_NAME), new String[] {
			"MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,20,M",
			"MEMBER_SUBSEQ,Subsequent purchase by member,ALWAYS,ALWAYS,10,M",
			"CENTENARY,Centenary Celebration in 2014,2014-01-01,365,15,A",
			"PRESIDENT_BDAY,University President's birthday,2014-02-01,365,20,A",
			"ORIENTATION_DAY,Orientation Day,2014-02-02,3,50,A"
			});
	}
	@Test
	public void testIntialLoadWhenThereisNoData() throws UssException {
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.INVALID_DISCOUNT_RECORDS);
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
	}
	
	@Test
	public void testCreateDiscountsAndGetAll() throws UssException, IOException {
		creteFileWithDiscountData();
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testDiscountDataAccess.getAll().size());
	
		DaySpecialDiscount discount1 = new DaySpecialDiscount("SG50", "SG50", 50, UssCommonUtil.convertStringToDate("2014-02-01"),7);
		DaySpecialDiscount discount2 = new DaySpecialDiscount("NATIONAL_DAY", "National Day Celebration in 2014", 25, UssCommonUtil.convertStringToDate("2014-08-09"),1);
		testDiscountDataAccess.create(discount1);
		testDiscountDataAccess.create(discount2);
		
		assertEquals(7, testDiscountDataAccess.getAll().size());
	}
	
	@Test
	public void testCreateExistingDiscountsAndGetAll() throws UssException, IOException {
		creteFileWithDiscountData();
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testDiscountDataAccess.getAll().size());
	
		DaySpecialDiscount discount = new DaySpecialDiscount("ORIENTATION_DAY", "Orientation day", 50, UssCommonUtil.convertStringToDate("2016-12-01"),7);
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.DISCOUNT_EXISTS);
		testDiscountDataAccess.create(discount);	
		assertEquals(5, testDiscountDataAccess.getAll().size());
	}
	
	@Test
	public void testUpdateDiscountsAndGetAll() throws UssException, IOException {
		creteFileWithDiscountData();
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		assertEquals(5, testDiscountDataAccess.getAll().size());
		Discount discount = new MemberOnlyDiscount("MEMBER_FIRST", "First purchase by member", 50);
		testDiscountDataAccess.update(discount);
		Discount test = testDiscountDataAccess.getDiscountByDiscountCode("MEMBER_FIRST");
		assertEquals(50, test.getDiscountPercentage(),0);
		assertEquals(5, testDiscountDataAccess.getAll().size());
		
	}
	
	public void testUpdateNonExistingDiscountsAndGetAll() throws UssException, IOException {
		creteFileWithDiscountData();
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testDiscountDataAccess.getAll().size());
		
		Discount discount = new MemberOnlyDiscount("MEMBER_SECOND", "Secnd purchase by member", 50);
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.DISCOUNT_NOT_EXIST);
		testDiscountDataAccess.update(discount);		
	}
}
