package sg.edu.nus.iss.uss.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.StoreKeeperFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.StoreKeeper;

public class AuthorisedServiceTest {

	private AuthorisedService authService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testValidStoreKeepers() throws UssException {
		AuthorisedService authService = new AuthorisedService(
				new MockStoreKeeperFileDataAccess() {
					@Override
					public List<StoreKeeper> getAll() {

						List<StoreKeeper> storeKeepers = new ArrayList<StoreKeeper>();

						StoreKeeper user = new StoreKeeper("andy", "123");

						storeKeepers.add(user);

						return storeKeepers;

					}
				});

		assertTrue(authService.isAuthorised("andy", "123"));
		assertTrue(authService.isAuthorised("Andy", "123"));
		assertFalse(authService.isAuthorised("andy", "1234"));

	}

	private void createTestFile() throws IOException {
		/*
		 * Create a test Storekeepers.dat file This file will contain
		 * 1.duplicate store keeper's name with different password 2. invalid
		 * input (no comma) 3. invalid input (no user name) 4. invalid input
		 * (user name with no password) 5. valid input (password with comma)
		 */

		File file = new File("data/Storekeepers.dat");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Andy,123");
		bw.newLine();

		bw.write("jacky,456");
		bw.newLine();

		bw.write("Mindy123");
		bw.newLine();

		bw.write("jacky,123");
		bw.newLine();

		bw.write(",123");
		bw.newLine();

		bw.write("michael,12,3");
		bw.newLine();

		bw.write("megatron,");
		bw.close();
	}

	private class MockStoreKeeperFileDataAccess extends
			StoreKeeperFileDataAccess {

		public MockStoreKeeperFileDataAccess() throws UssException {
			super();
		}

		@Override
		protected void initialLoad() {
			// do nothing
		}
	}
}
