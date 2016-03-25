package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

public class ProductService extends UssCommonService implements IProductService{
	
	private static final Object Null = null;

	private IProductDataAccess prdDataAccess;
	Product prodt;
    ArrayList<Integer> prdNos;
    
    
	public ProductService(IProductDataAccess prdDataAccess){
		this.prdDataAccess = prdDataAccess;
	}
	

	public Product getProductByProductID(String productID){
		Product product = null;
		for(Product prd:retrieveProductList()){
		     if (prd.getProductID().equals(productID)) {
		    	 product = prd;
		     }
		}
		return product;
	}
	
	
	public List<Product> retrieveProductList(){
		return prdDataAccess.getAll();
	}
	
	
	public List<Product> retrieveProductListByThreshold(){
		
		List<Product> prdList = new ArrayList<Product> ();
			
		for(Product Prd:retrieveProductList())
		{
		  if (Prd.isBelowThreshold()) prdList.add(Prd);
		}
		
		return prdList;
	}
	
	
	public void createNewProductEntry(String categoryCode, String productName, String briefDescription,int QuantityAvailable,  double price,
			int barCodeNumber, int reorderQuantity, int orderQuantity) throws UssException {
    	
		prdNos =  new ArrayList<Integer> ();
	    // Retrieve Product List Base on Category Code
	    for(Product Prd:retrieveProductList())
	    {
	       if (Prd.getProductID().toUpperCase().contains(categoryCode.toUpperCase())) 
	       {
	           prdNos.add(Prd.getProductNo());
	       }
	    }
	    
	    if (prdNos == Null) {
	    	categoryCode = categoryCode + "/1";	       
	    }
	    else
	    {
	        categoryCode = categoryCode + "/" + String.valueOf(Collections.max(prdNos,null) + 1);
	    }
	    
//	    prodt = new Product(categoryCode,productName,briefDescription,QuantityAvailable,price,barCodeNumber,reorderQuantity,orderQuantity);

        // Create Product and Write to File      
	    prdDataAccess.create(new Product(categoryCode,productName,briefDescription,QuantityAvailable,price,barCodeNumber,reorderQuantity,orderQuantity));

	}
    

    public boolean checkIfProductIsBelowThreshold (Product product) {
        return product.isBelowThreshold();
    }

    public void deductInventoryFromCheckout(List<Product> productItems) throws UssException {
	
        for(Product pdt : productItems)
    	{
        	        	
        	Product prod = getProductByProductID(pdt.getProductID());
        	
    	    if (prod == Null) {
    	    	// Product Item not valid
    	    }
    	    else {
	        	if (prod.getpurchaseQty() >= prod.getQuantityAvailable()) {
	        	        prod.setpurchaseQty(prod.getpurchaseQty());  //Update Qty
	        	        prdDataAccess.update(prodt); // Write to File
	        	}
		        else {
	    		    	//throws UssException as Purchased Quantity more than Quantity Available
		        }
        	}
    	}
            
   	}
    

}
