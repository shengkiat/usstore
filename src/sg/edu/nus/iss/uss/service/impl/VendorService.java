package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.IVendorDataAccess;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.service.IVendorService;

public class VendorService extends UssCommonService implements IVendorService{

	private IVendorDataAccess vendorDAO;

	public VendorService(IVendorDataAccess vendorDAO) {
		this.vendorDAO = vendorDAO;
	}
	
	@Override
	public List<Vendor> getVendorsByCategoryCode(String categoryCode) {

		Map<String, List<Vendor>> vendors = vendorDAO.getAll();

		if (vendors.containsKey(categoryCode)) {
			return Collections.unmodifiableList(vendors.get(categoryCode));
		} else {
			return new ArrayList<Vendor>();
		}

	}

}
