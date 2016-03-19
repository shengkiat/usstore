package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends UssCommonService implements IProductService{
	
	private List<Product> productList = null;
	
	@Override
	public Product getProductByProductID(String productID){
		//TODO TBI
		
		return null;
	}
	
	@Override
	public List<Product> retrieveProductList(){
		
		//TODO get product List from Products.dat file
		
		if(null == productList){
			productList = new ArrayList<Product>();
		}
		
		return productList;
	}
	
	@Override
	public List<Product> retrieveProductListByThreshold(int threshold){
		
		//TODO get product List from Products.dat file, filter by threshold
		
		if(null == productList){
			productList = new ArrayList<Product>();
		}
		
		return productList;
	}
	
	@Override
	public Product createNewProductEntry(String categoryCode, String productName, String briefDescription, int price,
			int barCodeNumber, int reorderQuantity, int orderQuantity){
		//TODO
		
		
		return null;
	}

	@Override
    public boolean checkIfProductIsBelowThreshold (Product product) {
        // todo to return true false to check if product inventory has fallen below threshold

        return false;
    }
	
	@Override
    public void deductInventoryFromCheckout(List<Product> productItems) {
        // todo: after payment, the list of product items bought will deduct the inventory.
    }

}
