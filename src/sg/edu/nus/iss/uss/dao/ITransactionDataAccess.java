package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Transaction;

public interface ITransactionDataAccess {
	
	public List<Transaction> getAll();
	public void create(List<Transaction> transactions) throws UssException;
}
