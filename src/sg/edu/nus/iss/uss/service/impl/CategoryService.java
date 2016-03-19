package sg.edu.nus.iss.uss.service.impl;

import java.util.List;

import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.service.ICategoryService;
import sg.edu.nus.iss.uss.dao.CategoryDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;

public class CategoryService extends UssCommonService implements ICategoryService{
	VendorService VendorSvc;
	CategoryDataAccess CatDataAccess;
	
	List<Category> CategoryList;
		
	public CategoryService(VendorService VendorSvc,CategoryDataAccess CatDataAccess){
		this.VendorSvc = VendorSvc;
		this.CatDataAccess = CatDataAccess;
	}
	
	public List<Category> retrieveCategoryList(){
		return CatDataAccess.getAll();
	}
	
	public void createNewCategory(String code, String Name)  throws UssException {
		
		categoryAdditionValidation(code);
		
	    Category Cat= new Category();
		Cat.setCode(code);
		Cat.setName(Name);
		CatDataAccess.create(Cat);
	
	}
	
	private void categoryAdditionValidation(String code) throws UssException  {
		
		for(Category cat: retrieveCategoryList())
		{
		    if (cat.getCode().equals(code)) throw new UssException(ErrorConstants.UssCode.CATEGORY, ErrorConstants.CATEGORY_EXISTS);
		}
		
	}
	
	public List<Vendor> CategoryVendor(String CategoryCode) {	
		return(VendorSvc.getVendorsByCategoryCode(CategoryCode));		
	}
}
