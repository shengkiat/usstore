package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.DiscountDataAccess;
import sg.edu.nus.iss.uss.model.Discount;

public class DiscountFileDataAccess extends FileDataAccess implements DiscountDataAccess {

	public DiscountFileDataAccess() {
		super("Discounts.dat");
	}

	@Override
	public List<Discount> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Discount e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void update(Discount e) {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected void initialLoad() {
		throw new RuntimeException("not implemented yet");
	}

}
