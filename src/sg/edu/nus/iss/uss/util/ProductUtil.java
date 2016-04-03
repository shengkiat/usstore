package sg.edu.nus.iss.uss.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.model.Product;

public class ProductUtil {
	
	private ProductUtil() {
	}
	
	public static Map<String, Integer> groupByProductId(List<Product> products) {
		
		Map<String, Integer> result = new HashMap<>();
		
		for(Product product : products) {
			Integer count = result.get(product.getProductID());
			if (count == null) {
				count = new Integer(0);
			}
			
			count++;
			
			result.put(product.getProductID(), count);
		}
		
		return result;
	}

}
