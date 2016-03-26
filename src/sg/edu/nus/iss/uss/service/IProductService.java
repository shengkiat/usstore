package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;

public interface IProductService {

	public Product getProductByProductID(String productID) throws UssException;

	public Product getProductByBarcode(String barcode) throws UssException;
	
	public List<Product> retrieveProductList();

	public List<Product> retrieveProductListByThreshold();

	public void replenishInventory(List<Product> productItems);
	
	public void createNewProductEntry(String categoryCode, String productName, String briefDescription, int QuantityAvailable, double price, 
			String barCodeNumber, int reorderQuantity, int orderQuantity) throws UssException;

	//public void updateProductEntry(String categoryCode, String productName, String briefDescription, int QuantityAvailable, double price, 
	//		String barCodeNumber, int reorderQuantity, int orderQuantity) throws UssException;
	
	public boolean checkIfProductIsBelowThreshold(Product product);

	public void deductInventoryFromCheckout(List<Product> productItems) throws UssException;

}
