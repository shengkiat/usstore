package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertEquals;

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

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.util.TestUtil;


/**
 * Created by Goh on 25/3/2016.
 */

public class CategoryServiceTest {

	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Category.dat";
	CategoryService categoryService = null;
	ICategoryDataAccess catDataAccess;
	VendorService VendorSvc;
	
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
			writer.write("CLO,Clothing");
			writer.newLine();
			writer.write("MUG,Mugs");
			writer.newLine();
			writer.write("STA,Stationary");
			writer.newLine();
			writer.write("DIA,Diary");
			writer.newLine();
			writer.write("SNA,Snacks");
			writer.newLine();
		} 
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//categoryService = new categoryService(VendorSvc,catDataAccess);
		
	}

	@After
	public void tearDown() throws Exception {
		categoryService = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}

	@Test
	public void testGetAll() {
		assertEquals(5, catDataAccess.getAll().size());
	}

	@Test
	public void testcreateNewCategory()  throws UssException {
		
		//categoryAdditionValidation(code);
	    Category e = new Category("BEV","Beverage");
		
		catDataAccess.create(e);
		assertEquals(6, catDataAccess.getAll().size());
		
	}
    
	@Test
    private void testcategoryAdditionValidation() throws UssException  {
		
        Category e = new Category("MUG","Mugs and Bottle");
		
		
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.CATEGORY_EXISTS);
		catDataAccess.create(e);
	}
	
	
}
