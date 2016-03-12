package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.edu.nus.iss.uss.dao.IDataAccess;
import sg.edu.nus.iss.uss.model.StoreKeeper;

public class StoreKeeperFileDataAccess extends FileDataAccess implements IDataAccess<StoreKeeper> {

	private List<StoreKeeper> storeKeppers = new ArrayList<StoreKeeper>();

	public StoreKeeperFileDataAccess() {
		super("Storekeepers.dat");
	}

	@Override
	public List<StoreKeeper> getAll() {

		List<String> lines = this.read();

		for (String line : lines) {

			List<String> storeKeeperInfo = Arrays.asList(line.split(","));

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
			}
			else{
				password = storeKeeperInfo.get(1);
			}

			StoreKeeper info = new StoreKeeper(storeKeeperInfo.get(0), password);

			this.addStoreKepper(info);
		}

		return storeKeppers;

	}

	@Override
	public void create(StoreKeeper e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void update(StoreKeeper e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	protected void initialLoad() {
		
	}

	private void addStoreKepper(StoreKeeper newStoreKepperInfo) {
		try {
			for (StoreKeeper storeKepperInfo : storeKeppers) {
				if (storeKepperInfo.getName().equals(newStoreKepperInfo.getName())) {
					storeKepperInfo.setPassword(newStoreKepperInfo.getPassword());
					return;
				}
			}

			this.storeKeppers.add(newStoreKepperInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
