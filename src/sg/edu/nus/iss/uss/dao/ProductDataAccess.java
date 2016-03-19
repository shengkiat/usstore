package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;

public interface ProductDataAccess {
	
	public List<Product> getAll();
	public void create(Product product) throws UssException;
	public void update(Product product) throws UssException;
}
