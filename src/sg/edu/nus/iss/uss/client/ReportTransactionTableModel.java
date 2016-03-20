package sg.edu.nus.iss.uss.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class ReportTransactionTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	private static final String[] COLUMN_NAMES = { "Transaction ID", "Product ID", "Product Name",
												   "Product Description", "Buyer", "Quantity Purchased",
												   "Date"};
	
	private Map<Integer, ReportTransaction> reportTransactions;
	
	public ReportTransactionTableModel(List<ReportTransaction> reportTransactions) {
		this.reportTransactions = new HashMap<>();
		for(int i = 0; i<reportTransactions.size(); i++) {
			this.reportTransactions.put(i, reportTransactions.get(i));
		}
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return reportTransactions.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		ReportTransaction reportTransaction = reportTransactions.get(row);
		return toArray(reportTransaction)[column];
	}
	
	private String[] toArray(ReportTransaction reportTransaction) {
		String[] result = new String[getColumnCount()];
		
		result[0] = "" + reportTransaction.getTransactionID();
		result[1] = reportTransaction.getProductID();
		result[2] = reportTransaction.getProductName();
		result[3] = reportTransaction.getProductBriefDescription();
		result[4] = reportTransaction.getBuyerID();
		result[5] = "" + reportTransaction.getQuantityPurchased();
		result[6] = UssCommonUtil.convertDateToString(reportTransaction.getDate());
		
		return result;
	}

}
