package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.util.TestUtil;

public class VendorFileDataAccessTest {
	
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Vendors%s.dat";
	
	private VendorFileDataAccess testDataAccess;
	
	private String testCategoryCode = "MUG";
	
	@Test
	public void testCreateShouldExistAfterExecute() {
		Category category = new Category();
		category.setCode(testCategoryCode);
		category.setName("Mug");
		
		Path path = getTestPath();
		
		assertFalse(Files.exists(path));
		
		testDataAccess = new VendorFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		Vendor vendor = new Vendor();
		vendor.setCategory(category);
		
		testDataAccess.create(vendor);
		
		assertTrue(Files.exists(path));
	}
	
	@After
	public void tearDown() throws IOException {
		testDataAccess = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + String.format(TEST_FILE_NAME, testCategoryCode));
	}
	

}
