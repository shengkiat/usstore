package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		return Paths.get(TEST_DATA_DIR);
	}

	@Before
	public void setUp() throws IOException {

		try {

			TestUtil.createFileWithLines(
					TestUtil.getTestPath("VendorsMUG.dat"), new String[] {
							"Nancy's Gift,Best of the best",
							"My Store,Nothing to sell", "Jacky Store,Pen",
							"Megatron Gift,Bad Boy Shop" });

			TestUtil.createFileWithLines(
					TestUtil.getTestPath("VendorsCLO.dat"), new String[] {
							"John's Gift,Best of the best",
							"Your Store,Nothing to sell", "Jacky Store,Pen",
							"Optimus Prime Gift,Bad Boy Shop" });

			TestUtil.createFileWithLines(
					TestUtil.getTestPath("VendorsShirt.dat"), new String[] {
							"John's Gift,Best of the best",
							"Your Store,Nothing to sell", "Jacky Store,Pen",
							"Optimus Prime Gift,Bad Boy Shop" });

		} catch (FileAlreadyExistsException e) {
			throw new RuntimeException("Test data file already exists: "
					+ e.getMessage());
		}
	}

}
