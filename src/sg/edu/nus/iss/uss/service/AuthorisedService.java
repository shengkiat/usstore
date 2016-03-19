package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sg.edu.nus.iss.uss.dao.StoreKeeperDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.StoreKeeperFileDataAccess;
import sg.edu.nus.iss.uss.model.StoreKeeper;
import sg.edu.nus.iss.uss.model.Vendor;

public class AuthorisedService extends UssCommonService {

	private StoreKeeperDataAccess storeKepperDAO;

	public AuthorisedService(StoreKeeperDataAccess storeKepperDAO) {

		this.storeKepperDAO = storeKepperDAO;
	}

	public boolean isAuthorised(String userName, String password) {
		try {
			for (StoreKeeper storeKepperInfo : this.storeKepperDAO.getAll()) {

				// ignore user name case
				int compare = storeKepperInfo.getName().compareToIgnoreCase(
						userName);

				if (compare == 0
						&& storeKepperInfo.getPassword().equals(password)) {
					return true;
				}

			}

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
