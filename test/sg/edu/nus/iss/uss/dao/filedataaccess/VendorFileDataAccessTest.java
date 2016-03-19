package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.VendorDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.service.VendorService;
import sg.edu.nus.iss.uss.util.TestUtil;

public class VendorFileDataAccessTest {

	private static final String TEST_DATA_DIR = TestUtil
			.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Vendors%s.dat";

	private VendorFileDataAccess testDataAccess;

	private String testCategoryCode = "MUG";

	public void testCreateShouldExistAfterExecute() throws UssException {
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

	@Test
	public void testReadActualData() throws UssException {

		VendorFileDataAccess testDataAccess1 = new VendorFileDataAccess();

		VendorService vendorService = new VendorService(testDataAccess1);

		assertEquals(0, vendorService.getVendorsByCategoryCode("mug").size());
		assertEquals(4, vendorService.getVendorsByCategoryCode("MUG").size());
		assertEquals(4, vendorService.getVendorsByCategoryCode("CLO").size());

		assertEquals(0, vendorService.getVendorsByCategoryCode("Shirt").size());
	}

	@Test
	public void testReadTestData() throws UssException {

		testDataAccess = new VendorFileDataAccess("", TEST_DATA_DIR);

		VendorService vendorService = new VendorService(testDataAccess);

		int p = vendorService.getVendorsByCategoryCode("MUG").size();

		assertEquals(0, vendorService.getVendorsByCategoryCode("mug").size());
		assertEquals(4, vendorService.getVendorsByCategoryCode("MUG").size());
		assertEquals(4, vendorService.getVendorsByCategoryCode("CLO").size());

		assertEquals(0, vendorService.getVendorsByCategoryCode("Shirt").size());
	}

	@After
	public void tearDown() throws IOException {
		testDataAccess = null;

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(
				Paths.get(TEST_DATA_DIR), "Vendors*.dat")) {
			for (Path file : stream) {

				Files.deleteIfExists(file);
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/"
				+ String.format(TEST_FILE_NAME, testCategoryCode));
	}

	@Before
	public void setUp() throws IOException {

		try {

			File file = new File(TEST_DATA_DIR + File.separator
					+ "VendorsMUG.dat");

			Files.createDirectories(this.getTestPath().getParent());

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

			file = new File(TEST_DATA_DIR + File.separator + "VendorsCLO.dat");

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

			file = new File(TEST_DATA_DIR + File.separator + "VendorsShirt.dat");

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

		} catch (FileAlreadyExistsException e) {
			throw new RuntimeException("Test data file already exists: "
					+ e.getMessage());
		}
	}

}
