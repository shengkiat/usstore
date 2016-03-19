package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.TransactionDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class TransactionFileDataAccess extends FileDataAccess implements TransactionDataAccess {
	
	private static final String FILE_NAME = "Transactions.dat";
	
	private static final int FIELD_TRANSACTION_ID = 0;
	private static final int FIELD_PRODUCT_ID = 1;
	private static final int FIELD_BUYER_ID = 2;
	private static final int FIELD_QUANTITY_PURCHASE = 3;
	private static final int FIELD_DATE = 4;
	
	private static final int TOTAL_FIELDS = 5;
	
	private List<Transaction> records;
	private int currentTransactionId;

	public TransactionFileDataAccess() {
		super(FILE_NAME);
		
		if (records == null) {
			records = new ArrayList<>();
		}
	}
	
	TransactionFileDataAccess(String fileName, String directory) {
		super(fileName, directory);	
	}
	
	@Override
	protected void initialLoad() {
		records = new ArrayList<>();
		currentTransactionId = 0;
		
		List<String[]> stringContent = readAll();
		for(String[] arr : stringContent) {
			int transactionId = Integer.parseInt(arr[FIELD_TRANSACTION_ID]);
			String productID = arr[FIELD_PRODUCT_ID];
			String buyerID = arr[FIELD_BUYER_ID];
			int quantityPurchased = Integer.parseInt(arr[FIELD_QUANTITY_PURCHASE]);
			Date date = UssCommonUtil.convertStringToDate(arr[FIELD_DATE]);
			Transaction transaction = new Transaction(productID, buyerID, quantityPurchased, date);
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
			String[] arr = new String[TOTAL_FIELDS];
			arr[FIELD_TRANSACTION_ID] = "" + currentTransactionId;
			arr[FIELD_PRODUCT_ID] = transaction.getProductID();
			arr[FIELD_BUYER_ID] = transaction.getBuyerID();
			arr[FIELD_QUANTITY_PURCHASE] = "" + transaction.getQuantityPurchased();
			arr[FIELD_DATE] = UssCommonUtil.convertDateToString(transaction.getDate());

			writeNewLine(arr);
			
			transaction.setTransactionID(currentTransactionId);
			records.add(transaction);
		}
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		StringBuilder builder = new StringBuilder();
		builder.append(arr[FIELD_TRANSACTION_ID]).append(getContentDelimiter()).append(arr[FIELD_DATE]);
		return builder.toString();
	}

	@Override
	protected int getTotalNumberOfFields() {
		return TOTAL_FIELDS;
	}
}
