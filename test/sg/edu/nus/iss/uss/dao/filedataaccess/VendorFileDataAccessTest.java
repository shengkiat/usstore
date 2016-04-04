package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.IVendorDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.util.TestUtil;

public class VendorFileDataAccessTest {

	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Vendors%s.dat";
	
	@Test
	public void testGetAllShouldAbleToGetCorrectNumbersOfRecords() throws IOException, UssException {
		IVendorDataAccess vendorDataAccess = new VendorFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		Map<String, List<Vendor>> result = vendorDataAccess.getAll();
		
		assertEquals(2, result.keySet().size());
	}
	
	@Test
	public void testGetAllShouldAbleToGetOnlyCategoryCodeWithThreeCharacters() throws IOException, UssException {

		TestUtil.createFileWithLines(
				TestUtil.getTestPath("VendorsSHIRT.dat"), new String[] {
						"John's Gift,Best of the best",
						"Your Store,Nothing to sell", "Jacky Store,Pen",
						"Optimus Prime Gift,Bad Boy Shop" });
		
		TestUtil.createFileWithLines(
				TestUtil.getTestPath("VendorsSTA.dat"), new String[] {
						"John's Gift,Best of the best",
						"Your Store,Nothing to sell", "Jacky Store,Pen",
						"Optimus Prime Gift,Bad Boy Shop" });
		
		IVendorDataAccess vendorDataAccess = new VendorFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		Map<String, List<Vendor>> result = vendorDataAccess.getAll();
		
		assertEquals(3, result.keySet().size());
	}

	@Test(expected=UssException.class)
	public void testInitialLoadShouldThrowExceptionWhenLessThanSpecifiedNoOfRecords() throws IOException, UssException {
		TestUtil.createFileWithLines(
				TestUtil.getTestPath("VendorsTES.dat"), new String[] {
						"John's Gift,Best of the best" });
		
		new VendorFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
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

		} catch (FileAlreadyExistsException e) {
			throw new RuntimeException("Test data file already exists: "
					+ e.getMessage());
		}
	}

	@After
	public void tearDown() throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(
				Paths.get(TEST_DATA_DIR), "Vendors*.dat")) {
			for (Path file : stream) {

				Files.deleteIfExists(file);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
