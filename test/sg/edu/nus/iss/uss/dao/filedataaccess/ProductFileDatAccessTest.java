package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.util.TestUtil;

public class ProductFileDatAccessTest {
	//Test fixtures
		private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
		private static final String TEST_FILE_NAME = "Products.dat";
		
		//@Rule
		//public ExpectedException exception = ExpectedException.none();
		
		private IProductDataAccess testProductDataAccess;
		
		
		@Before
		public void setUp() throws Exception {
			Path path = getTestPath();

	        Files.createDirectories(path.getParent());

	        try {
	            Files.createFile(path);
	        } catch (FileAlreadyExistsException e) {
	            throw new RuntimeException("Test data file already exists: " + e.getMessage());
	        }
		}

		@After
		public void tearDown() throws Exception {
			testProductDataAccess = null;
			
			TestUtil.destoryFile(TestUtil.getTestPath(TEST_FILE_NAME));
		}
		
		private Path getTestPath() {
			return Paths.get(TestUtil.getTestPath(TEST_FILE_NAME));
		}
		
		private void createFileWithProductData() throws IOException {
			TestUtil.createFileWithLines(TestUtil.getTestPath(TEST_FILE_NAME), new String[] {
				"CLO/1,Centenary Jumper,A really nice momento,315,21.45,1234,10,100",
				"CLO/2,Cotton Knit T-Shirt,Comfy ladies cotton knit button front Polo Tee,400,17.91,8970020033,200,300",
				"MUG/1,Centenary Mug,A really nice mug this time,525,10.25,9876,25,150",
				"MUG/2,Classic PC Water bottle,Crash Resistant Bottle,1000,8.91,5970030001,750,500",
				"STA/1,NUS PEN,A really cute blue pen,768,5.75,123459876,50,250",
				"STA/2,NUS Notepad,Great notepad for those lectures,1000,3.15,6789,25,75",
				"STA/3,NUS BookMark,A thoughtful gift for loved ones,500,4.41,8970020126,25,250",
				"STA/4,NUS Black Carbonite PEN,A perfect writing instrument,638,15.21,8970020082,150,400",
				"STA/5,NUS PEN Case,Stylish Pen Case,200,4.75,4549131138825,50,100",
				"DIA/1,A4 Port Folio,Ideal to hold Design work,100,10.90,54321,50,100",
				});
		}
		
		@Test(expected=UssException.class)
		public void testIntialLoadWhenThereisNoData() throws UssException {
			testProductDataAccess = new ProductFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		}

		@Test
		public void testcreateAndGetAll() throws UssException, IOException {
			createFileWithProductData();
			testProductDataAccess = new ProductFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
			
			assertEquals(10, testProductDataAccess.getAll().size());
		    
			Product product1 = new Product("MUG/3","PC Water Bottle","BPA Free PC Bottle",600,11.61,"5970050002",150,300);
			Product product2 = new Product("STA/6","Pentel PEN","Black Retractable Pen",1000,3.00,"438772222",100,1000);
					
			testProductDataAccess.create(product1);
			testProductDataAccess.create(product2);		
			
			assertEquals(12, testProductDataAccess.getAll().size());
			
		}
		
		@Test(expected=UssException.class)
		public void testCreateShouldThrowExceptionDueToExistingRecord() throws UssException, IOException {
			createFileWithProductData();
			testProductDataAccess = new ProductFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);

			Product product = new Product("MUG/1","Centenary Mug","A really nice mug this time",525,10.25,"9876",25,150);

	        //exception.expect(UssException.class);
			//exception.expectMessage(ErrorConstants.PRODUCT_EXISTS);
			testProductDataAccess.create(product);	
			//assertEquals(10, testProductDataAccess.getAll().size());
		}
		/*
		@Test
		public void testUpdateExistingCategoryAndGetAll() throws UssException, IOException {
			createFileWithProductData();
			testProductDataAccess = new ProductFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
			assertEquals(10, testProductDataAccess.getAll().size());
			
			// Change Description and adjust Price to 12.00
			Product product = new Product("MUG/1","Centenary Mug","A really nice mug",525,12.00,"9876",25,150);
			testProductDataAccess.update(product);
		    
		    
			Product p = testProductDataAccess.getProductByProductID("MUG/1");
			assertEquals("A really nice mug", p.getBriefDescription() ,"A really nice mug this time");
			assertEquals(10, testProductDataAccess.getAll().size());
		}
		@Test
		public void testUpdateNonExistingProductAndGetAll() throws UssException, IOException {
			createFileWithProductData();
			testProductDataAccess = new ProductFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
			assertEquals(10, testProductDataAccess.getAll().size());
			
			Product p = new Product("STA/6", "Green Stapler", "A Simple and Handy Tool",500,5.60,"43877112234",200,400);
			exception.expect(UssException.class);
			exception.expectMessage(ErrorConstants.CATEGORY_NOT_EXISTS);
			testProductDataAccess.update(p);		
		}*/
		
}