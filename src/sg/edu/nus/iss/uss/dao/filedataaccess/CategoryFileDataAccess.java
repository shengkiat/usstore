package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.uss.dao.ICategoryDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;

public class CategoryFileDataAccess extends FileDataAccess implements ICategoryDataAccess {

	private List<Category> categoryList;
	
	private static final String FILE_NAME = "Category.dat";
	private static final int FIELD_CATEGORY_CODE= 0;
	private static final int FIELD_NAME = 1;
	
	private static final int TOTAL_FIELDS = 2;
	
	private List<Category> catList;
	String[] strCat;
	
	public CategoryFileDataAccess() throws UssException {
		super(FILE_NAME);
	}

	public CategoryFileDataAccess(String fileName, String directory) throws UssException {
		super(fileName, directory);
	}
	
	@Override
	public List<Category> getAll() {
		return categoryList;
	}

	@Override
	public void create(Category e) throws UssException {	
		strCat = new String[TOTAL_FIELDS];
		
		strCat[FIELD_CATEGORY_CODE] = e.getCode();
		strCat[FIELD_NAME] = e.getName();
		
		writeNewLine(strCat);
	}

	public void update(Category e) throws UssException {
		if(e == null)
			throw new UssException(ErrorConstants.UssCode.CATEGORY, ErrorConstants.CATEGORY_NOT_EXISTS);
		for(Category tmp : getAll()) {
			if (e.getCode().equalsIgnoreCase(tmp.getCode()))
                catList.set(catList.indexOf(tmp), e);
		}
		
		strCat = new String[TOTAL_FIELDS];
		
		strCat[FIELD_CATEGORY_CODE] = e.getCode();
		strCat[FIELD_NAME] = e.getName();
		
		overwriteLine(strCat);
		
	}
	public Category getCategoryByCategoryCode(String CategoryCode) {
		Category e = null;
		for(Category cat:getAll()){
		     if (e.getCode().equals(CategoryCode)) {
		    	 e = cat;
		     }
		}
		return e;
		
	}
	
	@Override
	protected void initialLoad() {
		
		categoryList = new ArrayList<>();
		
		List<String[]> catList = readAll(); 
		
		for(String[] str: catList)
		{
			Category cat = new Category();
			cat.setCode(str[FIELD_CATEGORY_CODE]);
			cat.setName(str[FIELD_NAME]);
			categoryList.add(cat);
		}
	}
	

	
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[FIELD_CATEGORY_CODE];
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return TOTAL_FIELDS;
	}
}
