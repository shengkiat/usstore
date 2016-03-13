package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.TransactionDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;

public class TransactionService extends UssCommonService {
	
	private TransactionDataAccess transactionDataAccess;
	
	public TransactionService(TransactionDataAccess transactionDataAccess) {
		this.transactionDataAccess = transactionDataAccess;
	}
	
	public Transaction createNewTransaction(){
		return null;
	}
	
	public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate){
		return null;
	}

}
