package sg.edu.nus.iss.uss.service.impl;

import java.util.List;

import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.service.ICategoryService;

public class CategoryService extends UssCommonService implements ICategoryService{
	
	@Override
	public List<Category> retrieveCategoryList(){
		//TODO
		
		return null;
	}
	
	@Override
	public Category createNewCategory(String code, String Name){
		//TODO 
		
		return null;
	}
	
	private void categoryAdditionValidation(String code){
		//TODO
	}
}
