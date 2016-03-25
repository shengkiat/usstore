package sg.edu.nus.iss.uss.service.impl;

import java.util.List;

import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.service.ICategoryService;
import sg.edu.nus.iss.uss.service.IVendorService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;
import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;

public class CategoryService extends UssCommonService implements ICategoryService{
	private IVendorService vendorSvc;
	private ICategoryDataAccess catDataAccess;
		
	public CategoryService(IVendorService vendorService,ICategoryDataAccess CatDataAccess){
		this.vendorSvc = vendorService;
		this.catDataAccess = CatDataAccess;
	}
	
	public List<Category> retrieveCategoryList(){
		return catDataAccess.getAll();
	}
	
	public void createNewCategory(String code, String Name)  throws UssException {
		
		categoryAdditionValidation(code);
	    Category cat= new Category();
		cat.setCode(code);
		cat.setName(Name);
		catDataAccess.create(cat);

		
	}
	
	private void categoryAdditionValidation(String code) throws UssException  {
		
		for(Category cat: retrieveCategoryList())
		{
		    if (cat.getCode().equals(code)) throw new UssException(ErrorConstants.UssCode.CATEGORY, ErrorConstants.CATEGORY_EXISTS);
		}
		
	}
	
	public List<Vendor> CategoryVendor(String CategoryCode) {	
		return(vendorSvc.getVendorsByCategoryCode(CategoryCode));		
	}
}
