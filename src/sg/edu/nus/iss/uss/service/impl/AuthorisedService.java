package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.dao.IStoreKeeperDataAccess;
import sg.edu.nus.iss.uss.model.StoreKeeper;
import sg.edu.nus.iss.uss.service.IAuthorisedService;

public class AuthorisedService extends UssCommonService implements IAuthorisedService{

	private IStoreKeeperDataAccess storeKepperDAO;

	public AuthorisedService(IStoreKeeperDataAccess storeKepperDAO) {

		this.storeKepperDAO = storeKepperDAO;
	}

	@Override
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
