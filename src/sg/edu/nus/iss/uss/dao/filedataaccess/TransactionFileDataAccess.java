package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.TransactionDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class TransactionFileDataAccess extends FileDataAccess implements TransactionDataAccess {
	
	private static final String FILE_NAME = "Transactions.dat";
	
	private List<Transaction> records;
	private int currentTransactionId;

	public TransactionFileDataAccess() {
		super(FILE_NAME);
	}
	
	TransactionFileDataAccess(String fileName, String directory) {
		super(fileName, directory);	
	}
	
	@Override
	protected void initialLoad() {
		records = new ArrayList<>();
		currentTransactionId = 0;
		
		List<String> stringContent = readAll();
		for(String content : stringContent) {
			String[] arr = content.split(getContentDelimiter());
			int transactionId = Integer.parseInt(arr[0]);
			String productID = arr[1];
			String memberID = arr[2];
			int quantityPurchased = Integer.parseInt(arr[3]);
			Date date = UssCommonUtil.convertStringToDate(arr[4]);
			Transaction transaction = new Transaction(productID, memberID, quantityPurchased, date);
			transaction.setTransactionID(transactionId);
			
			if (transactionId > currentTransactionId) {
				currentTransactionId = transactionId;
			}
			
			records.add(transaction);
		}
	}

	@Override
	public List<Transaction> getAll() {
		return records;
	}

	@Override
	public void create(List<Transaction> transactions) {
		currentTransactionId++;
		for(Transaction transaction : transactions) {
			String[] arr = new String[5];
			arr[0] = "" + currentTransactionId;
			arr[1] = transaction.getProductID();
			arr[2] = transaction.getMemberID();
			arr[3] = "" + transaction.getQuantityPurchased();
			arr[4] = UssCommonUtil.convertDateToString(transaction.getDate());

			writeNewLine(arr);
			
			transaction.setTransactionID(currentTransactionId);
			records.add(transaction);
		}
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		throw new RuntimeException("not implemented yet");
	}
}
