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
		TableColumn transactionIdCol = getColumnModel().getColumn(ReportTransactionColumn.COLUMN_TRANSACTION_ID.getIndex());
		transactionIdCol.setPreferredWidth(20);
		
		TableColumn productIdCol = getColumnModel().getColumn(ReportTransactionColumn.COLUMN_PRODUCT_DESCRIPTION.getIndex());
		productIdCol.setPreferredWidth(10);
		
		TableColumn productNameCol = getColumnModel().getColumn(ReportTransactionColumn.COLUMN_PRODUCT_NAME.getIndex());
		productNameCol.setPreferredWidth(50);
		
		TableColumn productDescriptionCol = getColumnModel().getColumn(ReportTransactionColumn.COLUMN_PRODUCT_DESCRIPTION.getIndex());
		productDescriptionCol.setPreferredWidth(150);
		
		TableColumn buyerCol = getColumnModel().getColumn(ReportTransactionColumn.COLUMN_BUYER_ID.getIndex());
		buyerCol.setPreferredWidth(20);
	}
}
