package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.util.ProductUtil;

public class ProductService extends UssCommonService implements IProductService {

	private IProductDataAccess prdDataAccess;

	public ProductService(IProductDataAccess prdDataAccess) {
		this.prdDataAccess = prdDataAccess;
	}
	
	@Override
	public List<Product> retrieveProductList() {
		return prdDataAccess.getAll();
	}
	
	@Override
	public List<Product> retrieveProductListByThreshold() {

		List<Product> prdList = new ArrayList<Product>();

		for (Product Prd : retrieveProductList()) {
			if (Prd.isBelowThreshold())
				prdList.add(Prd);
		}

		return prdList;
	}
	
	@Override
	public void replenishInventory(List<Product> productItems) {
		
		List<Product> thresholdList;
		
		thresholdList = retrieveProductListByThreshold();
		
		for(Product p : productItems) {
			if (thresholdList.contains(p)) {
				int qty = p.getQuantityAvailable() + p.getOrderQuantity();
				p.setQuantityAvailable(qty);
				try {
					prdDataAccess.update(p);
				} catch (UssException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void createNewProductEntry(String categoryCode, String productName,
			String briefDescription, int QuantityAvailable, double price,
			String barCodeNumber, int reorderQuantity, int orderQuantity)
			throws UssException {
		
		if (getProductByBarCodeWithoutException(barCodeNumber) != null) {
			throw new UssException(ErrorConstants.UssCode.PRODUCT, ErrorConstants.PRODUCT_BARCODE_EXISTS);
		}
		
		
		List<Integer> prdNos = new ArrayList<>();
		// Retrieve Product List Base on Category Code
		for (Product prd : retrieveProductList()) {
			if (prd.getCategoryCode().equals(categoryCode.toUpperCase())){
				prdNos.add(prd.getProductNo());
			}
		}
		
		String productId = null;

		if (prdNos.isEmpty()) {
			productId = categoryCode + "/1";
		} else {
			productId = categoryCode + "/" + (Collections.max(prdNos) + 1);
		}

		// Create Product and Write to File
		prdDataAccess.create(new Product(productId, productName,
				briefDescription, QuantityAvailable, price, barCodeNumber,
				reorderQuantity, orderQuantity));

	}
	
	@Override
	public boolean checkIfProductIsBelowThreshold(Product product) {
		return product.isBelowThreshold();
	}
	
	@Override
	public void deductInventoryFromCheckout(List<Product> productItems)
			throws UssException {

		Map<String,Integer> productCountMap = groupByProductId(productItems);
		
		for(String productId : productCountMap.keySet()) {
			Product product = getProductByProductID(productId);
			
			int qtyPurchased = productCountMap.get(productId);
			int qtyAvailable = product.getQuantityAvailable();
            int qty = qtyAvailable - qtyPurchased;		
			
            if (qty >= 0) {

				product.setQuantityAvailable(qty); // Update Quantity after purchased

				prdDataAccess.update(product); // Write to File
			} else {

				throw new UssException(ErrorConstants.UssCode.PRODUCT, ErrorConstants.PRODUCT_QUANTITY_INSUFFICIENT);
				// throws UssException when Purchased Quantity more than Quantity Available
				// throws UssException as Purchased Quantity more than
				// Quantity Available
				//throw new UssException(ErrorConstants.UssCode.PRODUCT, ErrorConstants.PRODUCT_QTY_PURCHASE_MORE_THAN_AVAILABLE);
			}
		}

	}

	private Map<String, Integer> groupByProductId(List<Product> products) {
		return ProductUtil.groupByProductId(products);
	}
	
	@Override
	public Product getProductByProductID(String productID) throws UssException {
		Product p = null;
		for (Product prd : retrieveProductList()) {
			if (prd.getProductID().equals(productID)) {
				p = prd;
			}
		}
		return p;
	}

	@Override
	public Product getProductByBarcode(String barcode) throws UssException {
		Product p = getProductByBarCodeWithoutException(barcode);
		if (p == null) {
			throw new UssException(null, barcode);

		}

		return p;

	}
	
	private Product getProductByBarCodeWithoutException(String barcode) {
		Product p = null;
		for (Product prd : retrieveProductList()) {
			if (prd.getBarCodeNumber().equals(barcode)) {
				p = prd;
			}
		}
		
		return p;
	}

	@Override
	public boolean isProductStillAvailableInInventory(Product product, List<Product> productItems) {
		Map<String,Integer> productCountMap = groupByProductId(productItems);
		Integer currentCount = productCountMap.get(product.getProductID());
		return currentCount == null || ((product.getQuantityAvailable() - currentCount) > 0);
	}

}
