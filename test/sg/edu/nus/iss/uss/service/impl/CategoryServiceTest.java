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

import sg.edu.nus.iss.uss.dao.filedataaccess.CategoryFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.util.TestUtil;


public class CategoryServiceTest {

	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Category.dat";

	private CategoryService categoryService;

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
		
		categoryService = new CategoryService(new CategoryFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR));
		
	}

	@After
	public void tearDown() throws Exception {
		categoryService = null;
		
		Path path = getTestPath();

		TestUtil.destoryFile(TestUtil.getTestPath(TEST_FILE_NAME));
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}

	@Test
	public void testGetAll() {
		assertEquals(5, categoryService.retrieveCategoryList().size());
	}

	@Test
	public void testcreateNewCategory()  throws UssException {
		
		//categoryAdditionValidation(code);
	    
	    categoryService.createNewCategory("BEV","Beverage");
		assertEquals(6, categoryService.retrieveCategoryList().size());
		
	}
    
	@Test(expected=UssException.class)
    public void testcategoryAdditionValidation() throws UssException  {
		
		categoryService.createNewCategory("MUG","Mugs and Bottle");
		
	}
	
	
}
