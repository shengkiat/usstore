package sg.edu.nus.iss.uss.service;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AuthorisedServiceTest {

	private AuthorisedService authService;

	@Before
	public void setUp() throws Exception {

		this.createTestFile();

	}

	@Test
	public void testValidStoreKeepers() {
//		authService = new AuthorisedService();
//
//		assertTrue(authService.isAuthorised("andy", "123"));
//		assertTrue(authService.isAuthorised("jacky", "123"));
//		assertTrue(authService.isAuthorised("michael", "12,3"));
//
//		assertFalse(authService.isAuthorised("MEGatron", ""));

	}

	private void createTestFile() throws IOException {
		/*
		 * Create a test Storekeepers.dat file This file will contain 
		 * 1.duplicate store keeper's name with different password 
		 * 2. invalid input (no comma) 
		 * 3. invalid input (no user name) 
		 * 4. invalid input (user name with no password) 
		 * 5. valid input (password with comma)
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

}
