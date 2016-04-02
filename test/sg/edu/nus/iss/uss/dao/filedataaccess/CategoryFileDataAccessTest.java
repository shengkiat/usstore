package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.util.TestUtil;

public class CategoryFileDataAccessTest {
	
	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Category.dat";
	
	/*@Rule
	public ExpectedException exception = ExpectedException.none();
	*/
	private ICategoryDataAccess testCategoryDataAccess;
	
	
	
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
		testCategoryDataAccess = null;
		
		TestUtil.destoryFile(TestUtil.getTestPath(TEST_FILE_NAME));
	}
	
	private Path getTestPath() {
		return Paths.get(TestUtil.getTestPath(TEST_FILE_NAME));
	}
	
	private void createFileWithCategoryData() throws IOException {
		TestUtil.createFileWithLines(TestUtil.getTestPath(TEST_FILE_NAME), new String[] {
			"CLO,Clothing",
			"MUG,Mugs",
			"STA,Stationary",
			"DIA,Diary",
			"SNA,Snacks"
			});
	}
	
	@Test(expected=UssException.class)
	public void testIntialLoadWhenThereisNoData() throws UssException {
		testCategoryDataAccess = new CategoryFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
	}

	@Test
	public void testcreateCategoryAndGetAll() throws UssException, IOException {
		createFileWithCategoryData();
		testCategoryDataAccess = new CategoryFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testCategoryDataAccess.getAll().size());
	    
		Category category1 = new Category("ACC","Electronic Accessories");
		Category category2 = new Category("GIF","Gifts"); 
		
		testCategoryDataAccess.create(category1);
		testCategoryDataAccess.create(category2);		
		
		assertEquals(7, testCategoryDataAccess.getAll().size());
		
	}
	
	@Test(expected=UssException.class)
	public void testCreateShouldThrowExceptionDueToExistingRecord()  throws UssException, IOException {
		createFileWithCategoryData();
		testCategoryDataAccess = new CategoryFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testCategoryDataAccess.getAll().size());

		Category category = new Category("CLO","Clothing");

 		testCategoryDataAccess.create(category);	
		assertEquals(5, testCategoryDataAccess.getAll().size());
	}
	
	/*
	@Test
	public void testUpdateExistingCategoryAndGetAll() throws UssException, IOException {
		createFileWithCategoryData();
		testCategoryDataAccess = new CategoryFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testCategoryDataAccess.getAll().size());
		
		Category category = new Category("CLO","Clothings");
		testCategoryDataAccess.update(category);
		
		Category e = testCategoryDataAccess.getCategoryByCategoryCode("CLO");
		assertEquals("Clothings", e.getName(),"Clothing");
		assertEquals(5, testCategoryDataAccess.getAll().size());
	}
	
	@Test
	public void testUpdateNonExistingCategoryAndGetAll() throws UssException, IOException {
		createFileWithCategoryData();
		testCategoryDataAccess = new CategoryFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		assertEquals(5, testCategoryDataAccess.getAll().size());
		
		Category e = new Category("CLI", "Clothing");
		exception.expect(UssException.class);
		exception.expectMessage(ErrorConstants.CATEGORY_NOT_EXISTS);
		testCategoryDataAccess.update(e);		
	}
	*/
}
