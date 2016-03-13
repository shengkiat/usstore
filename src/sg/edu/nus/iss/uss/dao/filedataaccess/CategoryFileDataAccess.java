package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.CategoryDataAccess;
import sg.edu.nus.iss.uss.model.Category;

public class CategoryFileDataAccess extends FileDataAccess implements CategoryDataAccess {

	public CategoryFileDataAccess() {
		super("Category.dat");
	}

	@Override
	public List<Category> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Category e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	protected void initialLoad() {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return 0;
	}
}
