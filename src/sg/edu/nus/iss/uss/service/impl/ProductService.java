package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends UssCommonService implements IProductService{
	
	private IProductDataAccess prdDataAccess;
	
	public ProductService(IProductDataAccess prdDataAccess){
		this.prdDataAccess = prdDataAccess;
	}
	
	
	public Product getProductByProductID(String productID){
		//TODO TBI
		
		return null;
	}
	
	public List<Product> retrieveProductList(){
		return prdDataAccess.getAll();
	}
	
	public List<Product> retrieveProductListByThreshold(){
		
		List<Product> prdList = new ArrayList<>();
			
		for(Product Prd:retrieveProductList()){
		     if (Prd.isBelowThreshold()) {
		    	  prdList.add(Prd);
		     }
		}
		
		return prdList;
	}
	
	public Product createNewProductEntry(String categoryCode, String productName, String briefDescription, int price,
			int barCodeNumber, int reorderQuantity, int orderQuantity){
		//TODO
		
		
		return null;
	}


    public boolean checkIfProductIsBelowThreshold (Product product) {
        return product.isBelowThreshold();
    }

    public void deductInventoryFromCheckout(List<Product> productItems) {
        // todo: after payment, the list of product items bought will deduct the inventory.
    }


	@Override
	public List<Product> retrieveProductListByThreshold(int threshold) {
		// TODO Auto-generated method stub
		return null;
	}

}
