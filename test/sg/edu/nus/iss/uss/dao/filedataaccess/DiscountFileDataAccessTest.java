package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.DiscountDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.util.TestUtil;

public class DiscountFileDataAccessTest {
	
	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Discounts.dat";
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private DiscountDataAccess testDiscountDataAccess;

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
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}
	
	@Test
	public void testCreateOnceAndGetAll() throws ParseException, UssException {
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		List<Discount> discounts = new ArrayList<>();
		
		discounts.add(new MemberOnlyDiscount("MEMBER_FIRST", "First purchase by member", 20));
		discounts.add(new MemberOnlyDiscount("MEMBER_SUBSEQ", "Subsequent purchase by member", 10));
		discounts.add(new DaySpecialDiscount("CENTENARY", "Centenary Celebration in 2014", 15, df.parse("2014-01-01"),365));
		
		testDiscountDataAccess.create(discounts);
		
		List<Discount> records = testDiscountDataAccess.getAll();
		assertEquals(3, records.size());
		}
	
	@Test
	public void testCreateTwiceAndGetAll() throws ParseException, UssException {
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		List<Discount> firstDiscounts = new ArrayList<>();
		
		firstDiscounts.add(new MemberOnlyDiscount("MEMBER_FIRST", "First purchase by member", 20));
		firstDiscounts.add(new MemberOnlyDiscount("MEMBER_SUBSEQ", "Subsequent purchase by member", 10));
		firstDiscounts.add(new DaySpecialDiscount("CENTENARY", "Centenary Celebration in 2014", 15, df.parse("2014-01-01"),365));
		testDiscountDataAccess.create(firstDiscounts);
		
		List<Discount> secondDiscounts = new ArrayList<>();
		secondDiscounts.add(new DaySpecialDiscount("PRESIDENT_BDAY", "University President's birthday", 20, df.parse("2014-01-01"),365));
		secondDiscounts.add(new DaySpecialDiscount("ORIENTATION_DAY", "Centenary Celebration in 2014", 50, df.parse("2014-02-01"),7));
		testDiscountDataAccess.create(secondDiscounts);
		
		List<Discount> records = testDiscountDataAccess.getAll();
		assertEquals(5, records.size());
	}
	
	@Test
	public void testCreateAndGetAllWhenThereIsData() throws ParseException, UssException {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(TEST_DATA_DIR + File.separator + TEST_FILE_NAME), "utf-8"))) {
			writer.write("MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,20,M");
			writer.newLine();
			writer.write("MEMBER_SUBSEQ,Subsequent purchase by member,ALWAYS,ALWAYS,10,M");
			writer.newLine();
			writer.write("CENTENARY,Centenary Celebration in 2014,2014-01-01,365,15,A");
			writer.newLine();
			writer.write("PRESIDENT_BDAY,University President's birthday,2014-01-01,365,20,A");
			writer.newLine();
		} 
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		List<Discount> records = testDiscountDataAccess.getAll();
		assertEquals(4, records.size());
		
		List<Discount> discounts = new ArrayList<>();
		
		Discount discount1 = new DaySpecialDiscount("ORIENTATION_DAY", "Centenary Celebration in 2014", 50, df.parse("2014-02-01"),7);
		discounts.add(discount1);
		
		Discount discount2 = new DaySpecialDiscount("NATIONAL_DAY", "National Day Celebration in 2014", 25, df.parse("2014-08-09"),1);
		discounts.add(discount2);
		
		testDiscountDataAccess.create(discounts);
		
		records = testDiscountDataAccess.getAll();
		assertEquals(6, records.size());
	}
}
