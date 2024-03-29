package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.dao.IStoreKeeperDataAccess;
import sg.edu.nus.iss.uss.model.IUser;
import sg.edu.nus.iss.uss.service.IAuthorisedService;

public class AuthorisedService extends UssCommonService implements IAuthorisedService{

	private IStoreKeeperDataAccess storeKepperDAO;

	public AuthorisedService(IStoreKeeperDataAccess storeKepperDAO) {

		this.storeKepperDAO = storeKepperDAO;
	}

	@Override
	public boolean isAuthorised(String userName, String password) {
		
		for (IUser storeKepperInfo : this.storeKepperDAO.getAll()) {

			// ignore user name case
			if (storeKepperInfo.getName().equalsIgnoreCase(userName)
					&& storeKepperInfo.getPassword().equals(password)) {
				return true;
			}

		}

		return false;
	}


}
