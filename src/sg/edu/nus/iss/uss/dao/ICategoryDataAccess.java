package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Discount;

public interface ICategoryDataAccess {
	
	public List<Category> getAll();
	public void create(Category category) throws UssException;
	public void update(Category category) throws UssException;
	public Category getCategoryByCategoryCode(String discountCode);
	
}
