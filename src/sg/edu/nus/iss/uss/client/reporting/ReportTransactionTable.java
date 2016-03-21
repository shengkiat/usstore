package sg.edu.nus.iss.uss.client.reporting;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

final class ReportTransactionTable extends JTable {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	public ReportTransactionTable() {
		super(new ReportTransactionTableModel());
		
		adjustColumnWidth();
	}
	
	private void adjustColumnWidth() {
		TableColumn transactionIdCol = getColumnModel().getColumn(0);
		transactionIdCol.setPreferredWidth(20);
		
		TableColumn productIdCol = getColumnModel().getColumn(1);
		productIdCol.setPreferredWidth(10);
		
		TableColumn productNameCol = getColumnModel().getColumn(2);
		productNameCol.setPreferredWidth(50);
		
		TableColumn productDescriptionCol = getColumnModel().getColumn(3);
		productDescriptionCol.setPreferredWidth(150);
		
		TableColumn buyerCol = getColumnModel().getColumn(4);
		buyerCol.setPreferredWidth(20);
	}
}
