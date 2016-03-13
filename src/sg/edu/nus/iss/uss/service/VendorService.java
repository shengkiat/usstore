package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccess;
import sg.edu.nus.iss.uss.model.Vendor;

public class VendorService extends UssCommonService {

	private Map<String, List<Vendor>> vendorMap = new HashMap<String, List<Vendor>>();

	public VendorService() {
		try {
			this.getVendorsFromFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Vendor> getVendorsByCategoryCode(String categoryCode) {
		try {
			if (this.vendorMap.containsKey(categoryCode)) {
				return ((List) ((ArrayList) this.vendorMap.get(categoryCode)).clone());
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private void getVendorsFromFile() {
		try {
			VendorFileDataAccess vendorDAO = new VendorFileDataAccess();

			this.vendorMap = vendorDAO.getAll();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
