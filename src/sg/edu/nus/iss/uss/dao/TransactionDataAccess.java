package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Transaction;

public interface TransactionDataAccess {
	
	public List<Transaction> getAll();
	public void create(List<Transaction> transactions);
}