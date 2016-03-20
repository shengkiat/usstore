package sg.edu.nus.iss.uss.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.uss.model.ReportTransaction;

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
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
