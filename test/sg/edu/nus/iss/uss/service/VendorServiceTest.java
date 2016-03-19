package sg.edu.nus.iss.uss.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.service.impl.VendorService;

public class VendorServiceTest {

	@Test
	public void testGetVendorByCategoryCode() throws UssException {

		IVendorService vendorService = new VendorService(
				new MockVendorFileDataAccess() {
					@Override
					public Map<String, List<Vendor>> getAll() {

						Map<String, List<Vendor>> vendorMap = new HashMap<String, List<Vendor>>();

						Vendor vendormug1 = new Vendor();
						vendormug1.setName("Nancy");
						vendormug1
								.setDescription("Nancy's Gift,Best of the best");

						vendorMap.put("MUG", new ArrayList<Vendor>());
						vendorMap.get("MUG").add(vendormug1);

						return vendorMap;

					}
				});

		assertEquals(1, vendorService.getVendorsByCategoryCode("MUG").size());
		assertNotEquals(2, vendorService.getVendorsByCategoryCode("MUG").size());

		assertEquals(0, vendorService.getVendorsByCategoryCode("CLO").size());

	}

	private void createTestFile() throws IOException {
		/*
		 * Create a test Vendors*.dat file 1. invalid vendor file is created
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

	private class MockVendorFileDataAccess extends VendorFileDataAccess {

		public MockVendorFileDataAccess() throws UssException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void initialLoad() {
			// do nothing
		}
	}

}
