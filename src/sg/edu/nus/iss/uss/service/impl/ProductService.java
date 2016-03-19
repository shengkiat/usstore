package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

import java.util.List;

public class ProductService extends UssCommonService implements IProductService{
	IProductDataAccess PrdDataAccess;
	
	List<Product> PrdList;
	
	public ProductService(IProductDataAccess PrdDataAccess){
		this.PrdDataAccess = PrdDataAccess;
	}
	
	
	public Product getProductByProductID(String productID){
		//TODO TBI
		
		return null;
	}
	
	public List<Product> retrieveProductList(){
		return PrdDataAccess.getAll();
	}
	
	public List<Product> retrieveProductListByThreshold(){
			
		for(Product Prd:retrieveProductList()){
		     if (Prd.isBelowThreshold()) {
		    	  PrdList.add(Prd);
		     }
		}
		
		return PrdList;
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
