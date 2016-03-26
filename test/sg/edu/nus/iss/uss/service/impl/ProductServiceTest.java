package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.util.TestUtil;

/**
 * Created by Goh on 25/3/2016.
 */

public class ProductServiceTest {

	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Products.dat";
	CategoryService categoryService = null;
	ProductService productservice = null;
	ICategoryDataAccess catDataAccess;
	IProductDataAccess prdDataAccess;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		Path path = getTestPath();

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("Test data file already exists: " + e.getMessage());
        }
		
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(TEST_DATA_DIR + File.separator + TEST_FILE_NAME), "utf-8"))) {
			writer.write("CLO/1,Centenary Jumper,A really nice momento,9,21.45,1234,10,100");
			writer.newLine();
			writer.write("CLO/2,Cotton Knit T-Shirt,Comfy ladies cotton knit button front Polo Tee,400,17.91,8970020033,200,300");
			writer.newLine();
			writer.write("MUG/1,Centenary Mug,A really nice mug this time,525,10.25,9876,25,150");
			writer.newLine();
			writer.write("MUG/2,Classic PC,Water bottle,1000,8.91,5970030001,750,500");
			writer.newLine();
			writer.write("STA/1,NUS PEN,A really cute blue pen,768,5.75,123459876,50,250");
			writer.newLine();
			writer.write("STA/2,NUS Notepad,Great notepad for those lectures,1000,3.15,6789,25,75");
			writer.newLine();
			writer.write("STA/3,NUS BookMark,A thoughtful gift for loved ones,500,4.41,8970020126,25,250");
			writer.newLine();
			writer.write("STA/4,NUS Black Carbonite PEN,A perfect writing instrument,638,15.21,8970020082,150,400");
			writer.newLine();
			writer.write("STA/5,NUS PEN Case,Stylish Pen Case,40,4.75,4549131138825,50,100");
			writer.newLine();
			writer.write("DIA/1,A4 Port Folio,Ideal to hold Design work,100,10.90,54321,50,100");
			writer.newLine();
		} 
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//categoryService = new categoryService(VendorSvc,catDataAccess);
		
	}

	@After
	public void tearDown() throws Exception {
		productservice = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}
	
	@Test
	public void testGetAll() {
		assertEquals(10, prdDataAccess.getAll().size());
	}
	
	@Test
	public void testretrieveProductListByThreshold(){
		
		List<Product> prdList = productservice.retrieveProductListByThreshold();
		
		Product p1 = prdList.get(0);
		Product p2 = prdList.get(1);
		
		assertTrue(p1.getProductID().toString().equalsIgnoreCase("CLO/1"));
		assertTrue(p2.getProductID().toString().equalsIgnoreCase("STA/5"));
		
	}
	
	@Test
	public void createNewProductEntry(String categoryCode, String productName, String briefDescription, int QuantityAvailable, double price, 
			String barCodeNumber, int reorderQuantity, int orderQuantity) throws UssException {
		
	    Product p = new Product("BEV/1","BEVERAGE","PREMIUM SODA WATER",200,5.00,"8979920126",50,200);

        // Create Product and Write to File      
	    prdDataAccess.create(p);
		
	    assertEquals(11, prdDataAccess.getAll().size());
	    
	}
/*
	@Test
	public void testupdateProductEntry() throws UssException {
		
		Product p = new Product("MUG/1","Centenary Mug","A Nice Mug",525,10.00,"9876",25,150);
		
		prdDataAccess.update(p);
		
		Product p1 = prdDataAccess.getProductByProductID("MUG/1");
		
		//Test for Value After Update
		assertEquals(p1.getBriefDescription(), "A Nice Mug");
		assertEquals(String.valueOf(p1.getPrice()),"10.00");
		assertSame(p,p1);
	}
	*/
	
	@Test
    public void testcheckIfProductIsBelowThreshold () {
        
		Product p = new Product("MUG/2","Classic PC","Water bottle",99,8.91,"5970030001",100,500);
		Product p1 = new Product("MUG/2","Classic PC","Water bottle",700,8.91,"5970030001",100,500);
		// Test for Qty Below or Above Threshold
		assertTrue(p.isBelowThreshold());		
		assertFalse(p1.isBelowThreshold());
    }

	@Test
    public void testdeductInventoryFromCheckout() throws UssException {
	
        Product p = new Product("STA/3","NUS BookMark","A thoughtful gift for loved ones",500,4.41,"8970020126",25,250);
		
        p.setpurchaseQty(100);

        int QtyAvail = p.getQuantityAvailable();
        // QtyAvail = 500-100 = 400        
        assertEquals(QtyAvail, 400);
            
   	}
		
	
}
