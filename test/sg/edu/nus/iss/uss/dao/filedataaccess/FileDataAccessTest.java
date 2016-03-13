package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileDataAccessTest {
	
	private static final String TEST_DATA_DIR = System.getProperty("user.dir") + "\\test\\sg\\edu\\nus\\iss\\uss\\dao\\filedataaccess";
	private static final String TEST_FILE_NAME = "Testing.dat";
	
	private FileDataAccess testDataAccess;
	
	@Test
	public void testCreateShouldExistAfterExecute() {
		Path path = getTestPath();
		assertFalse(Files.exists(path));
		
		testDataAccess = new FileDataAccessImpl();
		testDataAccess.create();
		
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
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}
	
	private class FileDataAccessImpl extends FileDataAccess {

		protected FileDataAccessImpl() {
			super(TEST_FILE_NAME, TEST_DATA_DIR);
		}

		@Override
		protected void initialLoad() {
		}
		
	}

}
