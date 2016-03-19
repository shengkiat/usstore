package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.edu.nus.iss.uss.dao.IStoreKeeperDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.StoreKeeper;

public class StoreKeeperFileDataAccess extends FileDataAccess implements
		IStoreKeeperDataAccess {

	private List<StoreKeeper> storeKeppers;

	public StoreKeeperFileDataAccess() throws UssException {
		super("Storekeepers.dat");
	}

	public StoreKeeperFileDataAccess(String fileName, String directory) throws UssException {
		super(fileName, directory);
	}
	
	@Override
	public List<StoreKeeper> getAll() {

		return storeKeppers;

	}

	@Override
	protected void initialLoad() {
		
		this.storeKeppers  = new ArrayList<StoreKeeper>();
		
		List<String[]> lines = this.readAll();

		for (String[] line : lines) {

			List<String> storeKeeperInfo = Arrays.asList(line);

			if (storeKeeperInfo.size() < 2) {
				// no comma in line
				continue;
			} else if (storeKeeperInfo.get(0).equals("")) {
				// no username
				continue;
			}

			String password = "";

			if (storeKeeperInfo.size() > 2) {
				// password contains comma
				password = storeKeeperInfo.get(1);

				for (int i = 2; i < storeKeeperInfo.size(); i++) {
					password += "," + storeKeeperInfo.get(i);
				}
			} else {
				password = storeKeeperInfo.get(1);
			}

			StoreKeeper info = new StoreKeeper(storeKeeperInfo.get(0), password);

			this.addStoreKepper(info);
		}
	}

	private void addStoreKepper(StoreKeeper newStoreKepperInfo) {
		try {
			for (StoreKeeper storeKepperInfo : storeKeppers) {
				if (storeKepperInfo.getName().equals(
						newStoreKepperInfo.getName())) {
					storeKepperInfo.setPassword(newStoreKepperInfo
							.getPassword());
					return;
				}
			}

			this.storeKeppers.add(newStoreKepperInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getPrimaryKey(String[] arr) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	protected int getTotalNumberOfFields() {
		return 2;
	}
}
