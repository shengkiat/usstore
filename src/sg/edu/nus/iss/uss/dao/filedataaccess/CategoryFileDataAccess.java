package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.uss.dao.CategoryDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;

public class CategoryFileDataAccess extends FileDataAccess implements CategoryDataAccess {

	private List<Category> CategoryList;
	List<String[]> CatList;
	
	public CategoryFileDataAccess() throws UssException {
		super("Category.dat");
	}

	@Override
	public List<Category> getAll() {
		return CategoryList;
	}

	@Override
	public void create(Category e) {
		String[] strCat = new String[2];
		
		strCat[0] = e.getCode();
		strCat[1] = e.getName();
		
		writeNewLine(strCat);
	}

	@Override
	protected void initialLoad() {
		
		CatList = readAll(); 
		
		for(String[] str: CatList)
		{
			Category cat = new Category();
			cat.setCode(str[0]);
			cat.setName(str[1]);
			CategoryList.add(cat);
		}
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[0];
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return 2;
	}
}
