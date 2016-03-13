package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import sg.edu.nus.iss.uss.dao.TransactionDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class TransactionService extends UssCommonService {
	
	private TransactionDataAccess transactionDataAccess;
	
	public TransactionService(TransactionDataAccess transactionDataAccess) {
		this.transactionDataAccess = transactionDataAccess;
	}
	
	public void createTransactions(List<Transaction> transactions){
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

}
