package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import sg.edu.nus.iss.uss.dao.ITransactionDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class TransactionService extends UssCommonService implements ITransactionService{
	
	private ITransactionDataAccess transactionDataAccess;
	
	public TransactionService(ITransactionDataAccess transactionDataAccess) {
		this.transactionDataAccess = transactionDataAccess;
	}
	
	public void createTransactions(List<Transaction> transactions) throws UssException{
		transactionDataAccess.create(transactions);
	}
	
	public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate){
		Objects.requireNonNull(startDate, "startDate cannot be null");
		Objects.requireNonNull(endDate, "endDate cannot be null");
		
		if (UssCommonUtil.isDateLeftGreaterThanRight(startDate, endDate)) {
			throw new IllegalArgumentException("startDate cannot be greater than endDate");
		}
		
		List<Transaction> result = new ArrayList<>();
		List<Transaction> transactions = transactionDataAccess.getAll();
		
		for(Transaction transaction : transactions) {
			if (UssCommonUtil.isDateWithinRange(startDate, endDate, transaction.getDate())) {
				result.add(transaction);
			}
		}
		
		return result;
	}

	@Override
	public void createTransactions(List<Product> products, String memberID,
			Date transactionDate) throws UssException {
		throw new RuntimeException("");
	}

}