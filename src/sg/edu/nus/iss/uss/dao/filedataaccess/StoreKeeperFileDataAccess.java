package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.IDataAccess;
import sg.edu.nus.iss.uss.model.StoreKeeper;

public class StoreKeeperFileDataAccess extends FileDataAccess implements IDataAccess<StoreKeeper> {

	public StoreKeeperFileDataAccess() {
		super("Storekeepers.dat");
	}

	@Override
	public List<StoreKeeper> getAll() {
		throw new RuntimeException("not implemented yet");
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
		throw new RuntimeException("not implemented yet");
	}
}
