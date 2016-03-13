package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.ProductDataAccess;
import sg.edu.nus.iss.uss.model.Product;

public class ProductFileDataAccess extends FileDataAccess implements ProductDataAccess {

	public ProductFileDataAccess() {
		super("Products.dat");
	}

	@Override
	public List<Product> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Product e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void update(Product e) {
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
	
	
}
