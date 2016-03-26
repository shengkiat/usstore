package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.Transaction;

public interface ITransactionService {

	public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate);
	
	public void createTransactions(List<Product> products, String memberID, Date transactionDate) throws UssException;
	
}
