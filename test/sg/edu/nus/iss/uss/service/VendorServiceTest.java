package sg.edu.nus.iss.uss.service;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class VendorServiceTest {

	private VendorService vendorService;

	@Before
	public void setUp() throws Exception {
		this.createTestFile();
	}

	@Test
	public void testGetVendorByCategoryCode() {

		
		
		VendorService vendorService = new VendorService(new MockVendorFileDataAccess()
		{
		//@Override
//		public List<Vendor> getAll() {
//
//			return null;
//			
//		}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

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

	private class MockVendorFileDataAccess extends VendorFileDataAccess {
		
		@Override
		protected void initialLoad() {
			//do nothing
		}
	}
	
}
