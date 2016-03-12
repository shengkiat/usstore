package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Transaction;

public class TransactionFileDataAccess extends FileDataAccess implements IDataAccess<Transaction> {

	@Override
	public List<Transaction> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Transaction e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void update(Transaction e) {
		throw new RuntimeException("not implemented yet");
	}

}
