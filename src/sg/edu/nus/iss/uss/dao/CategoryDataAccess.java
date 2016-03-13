package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Category;

public interface CategoryDataAccess {
	
	public List<Category> getAll();
	public void create(Category category);
}