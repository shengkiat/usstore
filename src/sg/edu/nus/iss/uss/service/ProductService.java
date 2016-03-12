package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.uss.model.Product;

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
	

}
