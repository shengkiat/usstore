package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.model.Vendor;

public interface IVendorService {

	public List<Vendor> getVendorsByCategoryCode(String categoryCode);

}
