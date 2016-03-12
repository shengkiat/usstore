package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.VendorDataAccess;
import sg.edu.nus.iss.uss.model.Vendor;

public class VendorFileDataAccess extends FileDataAccess implements VendorDataAccess {

	public VendorFileDataAccess() {
		super("Vendors%.dat");
	}

	@Override
	public Map<String, List<Vendor>> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Vendor e) {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected void initialLoad() {
		throw new RuntimeException("not implemented yet");
	}
}
