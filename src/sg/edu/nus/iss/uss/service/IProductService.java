package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.model.Product;

public interface IProductService {

	public Product getProductByProductID(String productID);

	public List<Product> retrieveProductList();

	public List<Product> retrieveProductListByThreshold(int threshold);

	public Product createNewProductEntry(String categoryCode, String productName, String briefDescription, int price,
			int barCodeNumber, int reorderQuantity, int orderQuantity);

	public boolean checkIfProductIsBelowThreshold(Product product);

	public void deductInventoryFromCheckout(List<Product> productItems);

}
