package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.IDataAccess;
import sg.edu.nus.iss.uss.model.Category;

public class CategoryFileDataAccess extends FileDataAccess implements IDataAccess<Category> {

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
	public void update(Category e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	protected void initialLoad() {
		throw new RuntimeException("not implemented yet");
	}

}
