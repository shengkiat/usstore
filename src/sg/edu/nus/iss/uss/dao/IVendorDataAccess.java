package sg.edu.nus.iss.uss.dao;

import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.model.Vendor;

public interface IVendorDataAccess {
	
	public Map<String, List<Vendor>> getAll();

}
