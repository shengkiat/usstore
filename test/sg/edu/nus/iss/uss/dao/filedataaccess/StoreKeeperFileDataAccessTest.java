package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.service.impl.AuthorisedService;
import sg.edu.nus.iss.uss.service.IAuthorisedService;
import sg.edu.nus.iss.uss.util.TestUtil;

public class StoreKeeperFileDataAccessTest {

	private static final String TEST_DATA_DIR = TestUtil
			.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Storekeepers.dat";

	private StoreKeeperFileDataAccess testDataAccess;

	@Test
	public void testReadActualData() throws UssException {

		StoreKeeperFileDataAccess actualDataAccess = new StoreKeeperFileDataAccess();

		AuthorisedService authService = new AuthorisedService(actualDataAccess);

		assertTrue(authService.isAuthorised("andy", "123"));
		assertTrue(authService.isAuthorised("Andy", "123"));
		assertFalse(authService.isAuthorised("andy", "1234"));

	}

	@Test
	public void testReadTestData() throws UssException {

		testDataAccess = new StoreKeeperFileDataAccess("Storekeepers.dat",
				TEST_DATA_DIR);

		IAuthorisedService authService = new AuthorisedService(testDataAccess);

		assertTrue(authService.isAuthorised("andy", "123"));
		assertTrue(authService.isAuthorised("Andy", "123"));

		assertFalse(authService.isAuthorised("megatron", ""));
		assertFalse(authService.isAuthorised("andy", "1234"));

	}

	@After
	public void tearDown() throws IOException {
		testDataAccess = null;
		try {
			Files.deleteIfExists(this.getTestPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Path getTestPath() {
		return Paths.get(TestUtil.getTestPath(TEST_FILE_NAME));
	}

	@Before
	public void setUp() throws IOException {

		try {

			/*
			 * Create a test Storekeepers.dat file This file will contain
			 * 1.duplicate store keeper's name with different password 2.
			 * invalid input (no comma) 3. invalid input (no user name) 4.
			 * invalid input (user name with no password) 5. valid input
			 * (password with comma)
			 */

			TestUtil.createFileWithLines(TestUtil.getTestPath(TEST_FILE_NAME),
					new String[] { "Andy,123", "jacky,456", "Mindy123",
							"michael,12,3", "megatron,", "jacky,123" });

		} catch (FileAlreadyExistsException e) {
			throw new RuntimeException("Test data file already exists: "
					+ e.getMessage());
		}
	}

}