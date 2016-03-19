package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;

public interface ICategoryService {
	public List<Category> retrieveCategoryList();
	
	public void createNewCategory(String code, String Name) throws UssException;
	
}
