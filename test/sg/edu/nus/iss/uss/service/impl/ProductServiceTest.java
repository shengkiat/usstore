package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.ProductFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.TestProductBuilder;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.util.TestUtil;


public class ProductServiceTest {

	//Test fixtures
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Products.dat";
	
	private IProductService productservice; 

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
		
	    productservice = new ProductService(new ProductFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR));
	    		
	}

	@After
	public void tearDown() throws Exception {
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}
	
	@Test
	public void testRetrieveProductList() {
		assertEquals(10, productservice.retrieveProductList().size());
	}
	
	@Test
	public void testRetrieveProductListByThreshold(){
		
		List<Product> prdList = productservice.retrieveProductListByThreshold();
		
		Product p1 = prdList.get(0);
		Product p2 = prdList.get(1);
		
		assertTrue(p1.getProductID().toString().equalsIgnoreCase("CLO/1"));
		assertTrue(p2.getProductID().toString().equalsIgnoreCase("STA/5"));
		
	}
	
	@Test
	public void testReplenishInventory() throws UssException {
		
		int qty =0;
		
        List<Product> productItems = new ArrayList<Product> ();
		
		Product p1 = productservice.getProductByProductID("CLO/1");
		assertNotNull(p1);
				
        productItems.add(p1);

		Product p2 = productservice.getProductByProductID("STA/5");
		assertNotNull(p2);
		
        productItems.add(p2);
		
		int p1Qty = p1.getQuantityAvailable() + p1.getReorderQuantity();
		int p2Qty = p2.getQuantityAvailable() + p2.getReorderQuantity();
				
		productservice.replenishInventory(productItems);
		
		p1 = productservice.getProductByProductID("CLO/1");
	    
	    qty = p1.getQuantityAvailable();
		
        assertEquals(qty, p1Qty) ;
		
        p2 = productservice.getProductByProductID("STA/5");
	    
        qty = p2.getQuantityAvailable();
        
        assertEquals(qty, p2Qty) ;
		
	}
	
	
	@Test
	public void testCreateNewProductEntry() throws UssException {
		
	    Product p = new Product("BEV/1","BEVERAGE","PREMIUM SODA WATER",200,5.00,"8979920126",50,200);

        // Create Product and Write to File
	    productservice.createNewProductEntry(p.getProductID(),p.getName(),p.getBriefDescription(),p.getQuantityAvailable(),p.getPrice(),p.getBarCodeNumber(),p.getReorderQuantity(),p.getOrderQuantity());
	    
	    assertEquals(11, productservice.retrieveProductList().size());
	    
	}
	
	
	
	@Test
    public void testCheckIfProductIsBelowThreshold () {
		
		TestProductBuilder testProductBuilder = new TestProductBuilder();
        
		Product p = testProductBuilder.withReorderQuantity(100).withQuantityAvailable((99)).build();
		Product p1 = testProductBuilder.withReorderQuantity(100).withQuantityAvailable((700)).build();
		
		// Test for Qty Below or Above Threshold
		assertTrue(productservice.checkIfProductIsBelowThreshold(p));		
		assertFalse(productservice.checkIfProductIsBelowThreshold(p1));
		
		//productservice.checkIfProductIsBelowThreshold
    }

	@Test
    public void testdeductInventoryFromCheckout() throws UssException {
        
		List<Product> productItems = new ArrayList<Product> ();
		
		Product product = productservice.getProductByProductID("CLO/1");
		assertNotNull(product);
		
		int qtyAvailable = product.getQuantityAvailable() - 2;
		
        TestProductBuilder testProductBuilder = new TestProductBuilder();
        
		Product p = testProductBuilder.withProductID("CLO/1").withQuantityAvailable(product.getQuantityAvailable()).build();
		Product p1 = testProductBuilder.withProductID("CLO/1").withQuantityAvailable(product.getQuantityAvailable()).build();
		
		productItems.add(p);
		productItems.add(p1);
		
		productservice.deductInventoryFromCheckout(productItems);
		
		product = productservice.getProductByProductID("CLO/1");
     
        assertEquals(product.getQuantityAvailable(), qtyAvailable);
            
   	}
		
	
}
