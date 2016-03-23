package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;

import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class ProductService extends UssCommonService implements IProductService{
	
	private static final Object Null = null;

	private IProductDataAccess prdDataAccess;
	private ICategoryDataAccess catDataAccess;
	Product prodt;
    Category category;
    ArrayList<Product> catPrdList;
    
    
	public ProductService(IProductDataAccess prdDataAccess,ICategoryDataAccess catDataAccess){
		this.prdDataAccess = prdDataAccess;
		this.catDataAccess = catDataAccess;
	}
	
	public void refresh_CatPrdList(){
		List<Category> catList = catDataAccess.getAll();
		List<Product> prdList = prdDataAccess.getAll();
		
		UssCommonUtil.catPrdHMap.clear();
		
		for(Category cat : catList)
		{
			if (!UssCommonUtil.catPrdHMap.containsKey(cat)) 
			{
				for(Product prd : prdList)
				{
                    ArrayList<Product> pList = new ArrayList<Product> ();
                    UssCommonUtil.catPrdHMap.put(cat, pList);
                    if (prd.getCategoryCode().toUpperCase().contains(cat.getCode().toUpperCase())) {
                		pList.add(prd);
                	}
				}	
			}
		}
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
	
	public Category getCategory(String categorycode){
		for(Category cat: retrieveCategoryList())
		{
		   if (cat.getCode().toUpperCase().equals(categorycode.toUpperCase())) 
			   category = cat;
		}
        return category;   
	}
	
	
	public List<Product> retrieveProductList(){
		return prdDataAccess.getAll();
	}
	
	public List<Category> retrieveCategoryList(){
		return catDataAccess.getAll();
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
    	
		int maxPrdNo = 0;
		/*
		List<Product> prdList = new ArrayList<Product>();
	    
	    // Retrieve Product List Base on Category Code
	    for(Product Prd:retrieveProductList())
	    {
	       if (Prd.getProductID().toUpperCase().contains(categoryCode.toUpperCase())) 
	       {
	           prdList.add(Prd);
	       }
	    }
	    Collections.sort(prdList, new Product.ProductByIDNo());
	    
	    Iterator<Product> i = prdList.iterator();
	    while (i.hasNext()) {
        	 i.next();
        }

	    String sPrdID = categoryCode + "/" + String.valueOf(i.next().getProductNo()+1);
	    	    
	    prdDataAccess.create(new Product(sPrdID,productName,briefDescription,QuantityAvailable,price,barCodeNumber,reorderQuantity,orderQuantity));
	    */
     	/////////////////////////////////////////////////////////////////////////////////////////////////
				    		
			
	    catPrdList =  UssCommonUtil.catPrdHMap.get(getCategory(categoryCode));
		
	    String sPID;
	    
	    if (catPrdList == Null) {
	    	sPID = categoryCode + "/1";
	    	catPrdList = new ArrayList<Product>();
	    	UssCommonUtil.catPrdHMap.put(category, catPrdList);
	    }        
	    else	
	    {	
            Iterator<Product> i = catPrdList.iterator();
            while (i.hasNext()) {
            	if (i.next().getProductNo() > maxPrdNo) {
            		maxPrdNo = i.next().getProductNo();
            	}
            }
            sPID = categoryCode + "/" + String.valueOf(maxPrdNo + 1);
	    }
	        
	    Product pDt = new Product(sPID,productName,briefDescription,QuantityAvailable,price,barCodeNumber,reorderQuantity,orderQuantity);
	    
	    pDt.setProductNo(maxPrdNo + 1);
	    
        catPrdList.add(pDt);
	    
	    prdDataAccess.create(pDt);
    	
	}
    

    public boolean checkIfProductIsBelowThreshold (Product product) {
        return product.isBelowThreshold();
    }

    public void deductInventoryFromCheckout(List<Product> productItems) throws UssException {
        
        for(Product prodt : productItems)
    	{
        	        	
        	Category cat = getCategory(prodt.getCategoryCode());
        	
        	ArrayList<Product> prdLst = UssCommonUtil.catPrdHMap.get(cat);
        	
    	    if (prdLst == Null) {
    	    	// Product Item not valid
    	    }
    	    else {
	        	Product pDt = prdLst.get(prdLst.indexOf(prodt));
	        	
	        	if (prodt.getpurchaseQty() >= pDt.getQuantityAvailable()) {
	        		
	        	        pDt.setpurchaseQty(prodt.getpurchaseQty());
	        	        prdDataAccess.update(pDt); // Write to File
	       	    	 
	        	}
		        else {
	    		    	//throws UssException as Purchased Quantity more than Quantity Available
		        }
        	}
    	}
            
   	}
    

}
