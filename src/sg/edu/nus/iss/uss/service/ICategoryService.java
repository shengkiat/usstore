package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.model.Category;

public interface ICategoryService {
	public List<Category> retrieveCategoryList();
	
	public Category createNewCategory(String code, String Name);
	
}
