package sg.edu.nus.iss.uss.client.reporting;

import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

final class ReportTransactionTableModel extends ReportTableModel<ReportTransaction> {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	private static final String[] COLUMN_NAMES = { "Transaction ID", "Product ID", "Product Name",
												   "Product Description", "Buyer", "Quantity Purchased",
												   "Date"};
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	@Override
	protected String[] toArray(ReportTransaction reportTransaction) {
		String[] result = new String[getColumnCount()];
		
		result[ReportTransactionColumn.COLUMN_TRANSACTION_ID.getIndex()] = "" + reportTransaction.getTransactionID();
		result[ReportTransactionColumn.COLUMN_PRODUCT_ID.getIndex()] = reportTransaction.getProductID();
		result[ReportTransactionColumn.COLUMN_PRODUCT_NAME.getIndex()] = reportTransaction.getProductName();
		result[ReportTransactionColumn.COLUMN_PRODUCT_DESCRIPTION.getIndex()] = reportTransaction.getProductBriefDescription();
		result[ReportTransactionColumn.COLUMN_BUYER_ID.getIndex()] = reportTransaction.getBuyerID();
		result[ReportTransactionColumn.COLUMN_QUANTITY_PURCHASE.getIndex()] = "" + reportTransaction.getQuantityPurchased();
		result[ReportTransactionColumn.COLUMN_DATE.getIndex()] = UssCommonUtil.convertDateToString(reportTransaction.getDate());
		
		return result;
	}

}
