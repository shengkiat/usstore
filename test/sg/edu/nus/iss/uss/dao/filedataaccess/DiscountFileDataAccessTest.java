package sg.edu.nus.iss.uss.dao.filedataaccess;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.IDiscountDataAccess;
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
	
	@Test
	public void testCreateOnceAndGetAll() throws ParseException, UssException {
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		MemberOnlyDiscount discount = new MemberOnlyDiscount("MEMBER_FIRST", "First purchase by member", 20);
		//discounts.add(new MemberOnlyDiscount("MEMBER_SUBSEQ", "Subsequent purchase by member", 10));
		//discounts.add(new DaySpecialDiscount("CENTENARY", "Centenary Celebration in 2014", 15, df.parse("2014-01-01"),365));
		
		testDiscountDataAccess.create(discount);
		
		List<Discount> records = testDiscountDataAccess.getAll();
		assertEquals(1, records.size());
		}
	
	@Test
	public void testCreateTwiceAndGetAll() throws ParseException, UssException {
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		MemberOnlyDiscount firstDiscount1 = new MemberOnlyDiscount("MEMBER_FIRST", "First purchase by member", 20);
		//firstDiscounts.add(new MemberOnlyDiscount("MEMBER_SUBSEQ", "Subsequent purchase by member", 10));
		//firstDiscounts.add(new DaySpecialDiscount("CENTENARY", "Centenary Celebration in 2014", 15, df.parse("2014-01-01"),365));
		testDiscountDataAccess.create(firstDiscount1);
		
		DaySpecialDiscount secondDiscount2 = new DaySpecialDiscount("PRESIDENT_BDAY", "University President's birthday", 20, df.parse("2014-01-01"),365);
		//secondDiscounts.add(new DaySpecialDiscount("ORIENTATION_DAY", "Centenary Celebration in 2014", 50, df.parse("2014-02-01"),7));
		testDiscountDataAccess.create(secondDiscount2);
		
		List<Discount> records = testDiscountDataAccess.getAll();
		assertEquals(2, records.size());
	}
	
	@Test
	public void testCreateAndGetAllWhenThereIsData() throws ParseException, UssException, IOException {
		
		TestUtil.createFileWithLines(TestUtil.getTestPath(TEST_FILE_NAME), new String[] {
				"MEMBER_FIRST,First purchase by member,ALWAYS,ALWAYS,20,M",
				"MEMBER_SUBSEQ,Subsequent purchase by member,ALWAYS,ALWAYS,10,M",
				"CENTENARY,Centenary Celebration in 2014,2014-01-01,365,15,A",
				"PRESIDENT_BDAY,University President's birthday,2014-01-01,365,20,A" 
		});
		
		testDiscountDataAccess = new DiscountFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		List<Discount> records = testDiscountDataAccess.getAll();
		assertEquals(4, records.size());
		
		
		Discount discount1 = new DaySpecialDiscount("ORIENTATION_DAY", "Centenary Celebration in 2014", 50, df.parse("2014-02-01"),7);
		
		//Discount discount2 = new DaySpecialDiscount("NATIONAL_DAY", "National Day Celebration in 2014", 25, df.parse("2014-08-09"),1);
		//discounts.add(discount2);
		
		testDiscountDataAccess.create(discount1);
		
		records = testDiscountDataAccess.getAll();
		assertEquals(5, records.size());
	}
}
