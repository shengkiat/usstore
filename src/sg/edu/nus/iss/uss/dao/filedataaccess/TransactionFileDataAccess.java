package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.uss.dao.TransactionDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class TransactionFileDataAccess extends FileDataAccess implements TransactionDataAccess {
	
	private static final String FILE_NAME = "Transactions.dat";
	
	private List<Transaction> records = new ArrayList<>();
	
	private int currentTransactionId = 0;

	public TransactionFileDataAccess() {
		super(FILE_NAME);
	}
	
	TransactionFileDataAccess(String fileName, String directory) {
		super(fileName, directory);	
	}
	
	@Override
	protected void initialLoad() {
		
	}

	@Override
	public List<Transaction> getAll() {
		return records;
	}

	@Override
	public void create(List<Transaction> transactions) {
		StringBuilder builder = new StringBuilder();
		currentTransactionId++;
		for(Transaction transaction : transactions) {
			builder.append(currentTransactionId).append(",");
			builder.append(transaction.getProductID()).append(",");
			builder.append(transaction.getMemberID()).append(",");
			builder.append(transaction.getQuantityPurchased()).append(",");
			builder.append(UssCommonUtil.convertDateToString(transaction.getDate()));
			write(builder.toString());
			
			transaction.setTransactionID(currentTransactionId);
			records.add(transaction);
		}
	}

}
