package sg.edu.nus.iss.uss.service;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class VendorServiceTest {

	private VendorService vendorService;

	@Before
	public void setUp() throws Exception {
		this.createTestFile();
	}

	@Test
	public void testGetVendorByCategoryCode() {

		vendorService = new VendorService();

		assertNotNull(vendorService.getVendorsByCategoryCode("MUG"));
		assertNotNull(vendorService.getVendorsByCategoryCode("CLO"));

		assertNull(vendorService.getVendorsByCategoryCode("Shirt"));

	}

	private void createTestFile() throws IOException {
		/*
		 * Create a test Vendors*.dat file 
		 * 1. invalid vendor file is created
		 */

		File file = new File("data" + File.separator + "VendorsMUG.dat");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Nancy's Gift,Best of the best");
		bw.newLine();

		bw.write("My Store,Nothing to sell");
		bw.newLine();

		bw.write("Jacky Store,Pen");
		bw.newLine();

		bw.write("Megatron Gift,Bad Boy Shop");
		bw.close();

		file = new File("data" + File.separator + "VendorsCLO.dat");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		fw = new FileWriter(file.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write("John's Gift,Best of the best");
		bw.newLine();

		bw.write("Your Store,Nothing to sell");
		bw.newLine();

		bw.write("Jacky Store,Pen");
		bw.newLine();

		bw.write("Optimus Prime Gift,Bad Boy Shop");
		bw.close();

		file = new File("data" + File.separator + "VendorsShirt.dat");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		fw = new FileWriter(file.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write("John's Gift,Best of the best");
		bw.newLine();

		bw.write("Your Store,Nothing to sell");
		bw.newLine();

		bw.write("Jacky Store,Pen");
		bw.newLine();

		bw.write("Optimus Prime Gift,Bad Boy Shop");
		bw.close();

	}

}
