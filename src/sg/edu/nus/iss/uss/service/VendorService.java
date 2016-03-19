package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.VendorDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccess;
import sg.edu.nus.iss.uss.model.Vendor;

public class VendorService extends UssCommonService {

	private VendorDataAccess vendorDAO;

	public VendorService(VendorDataAccess vendorDAO) {
		this.vendorDAO = vendorDAO;
	}

	public List<Vendor> getVendorsByCategoryCode(String categoryCode) {

		Map<String, List<Vendor>> vendors = vendorDAO.getAll();

		if (vendors.containsKey(categoryCode)) {
			return Collections.unmodifiableList(vendors.get(categoryCode));
		} else {
			return new ArrayList<Vendor>();
		}

	}

}
