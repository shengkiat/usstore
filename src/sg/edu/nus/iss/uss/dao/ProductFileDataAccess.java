package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Product;

public class ProductFileDataAccess extends FileDataAccess implements IDataAccess<Product> {

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

}
