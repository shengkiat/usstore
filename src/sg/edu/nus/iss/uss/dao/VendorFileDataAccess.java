package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Vendor;

public class VendorFileDataAccess extends FileDataAccess implements IDataAccess<Vendor> {

	public VendorFileDataAccess() {
		super("Vendors%.dat");
	}

	@Override
	public List<Vendor> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Vendor e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void update(Vendor e) {
		throw new RuntimeException("not implemented yet");
	}

}
