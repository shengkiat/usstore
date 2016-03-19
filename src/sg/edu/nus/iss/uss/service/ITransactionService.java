package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.model.Transaction;

public interface ITransactionService {

	public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate);

	public void createTransactions(List<Transaction> transactions);
	
}
