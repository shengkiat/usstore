package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Discount;

public class DiscountFileDataAccess extends FileDataAccess implements IDataAccess<Discount> {

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

}
