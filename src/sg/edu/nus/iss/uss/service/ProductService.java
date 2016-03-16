package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends UssCommonService {
	
	private List<Product> productList = null;
	
	public Product getProductByProductID(String productID){
		//TODO TBI
		
		return null;
	}
	
	public List<Product> retrieveProductList(){
		
		//TODO get product List from Products.dat file
		
		if(null == productList){
			productList = new ArrayList<Product>();
		}
		
		return productList;
	}
	
	public List<Product> retrieveProductListByThreshold(int threshold){
		
		//TODO get product List from Products.dat file, filter by threshold
		
		if(null == productList){
			productList = new ArrayList<Product>();
		}
		
		return productList;
	}
	
	public Product createNewProductEntry(String categoryCode, String productName, String briefDescription, int price,
			int barCodeNumber, int reorderQuantity, int orderQuantity){
		//TODO
		
		
		return null;
	}

    // added by Jia Cheng on 15/03/2016
    public boolean checkIfProductIsBelowThreshold (Product product) {
        // todo to return true false to check if product inventory has fallen below threshold

        return false;
    }

}
