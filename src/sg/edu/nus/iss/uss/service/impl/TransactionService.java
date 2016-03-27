package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	@Override
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
	public void createTransactions(List<Product> products, String memberID, Date transactionDate) throws UssException {
		
		Objects.requireNonNull(products, "products cannot be null");
		Objects.requireNonNull(memberID, "memberID cannot be null");
		Objects.requireNonNull(transactionDate, "transactionDate cannot be null");
		
		Map<String, Integer> productBought = groupByProductId(products);
		List<Transaction> transactions = new ArrayList<>();
		
		
		for(String productId : productBought.keySet()) {
			int quantityPurchased = productBought.get(productId);
			Transaction transaction = new Transaction(productId, memberID, quantityPurchased, transactionDate);
			
			transactions.add(transaction);
		}
		
		transactionDataAccess.create(transactions);
	}

	private Map<String, Integer> groupByProductId(List<Product> products) {
		
		Map<String, Integer> result = new HashMap<>();
		
		for(Product product : products) {
			Integer count = result.get(product.getProductID());
			if (count == null) {
				count = new Integer(0);
			}
			
			count++;
			
			result.put(product.getProductID(), count);
		}
		
		return result;
	}
}