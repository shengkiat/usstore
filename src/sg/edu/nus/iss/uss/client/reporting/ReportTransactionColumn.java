package sg.edu.nus.iss.uss.client.reporting;

enum ReportTransactionColumn {
	
	COLUMN_TRANSACTION_ID(0),
	COLUMN_PRODUCT_ID(1),
	COLUMN_PRODUCT_NAME(2),
	COLUMN_PRODUCT_DESCRIPTION(3),
	COLUMN_BUYER_ID(4),
	COLUMN_QUANTITY_PURCHASE(5),
	COLUMN_DATE(6);
	
	private final int index;
	
	private ReportTransactionColumn(int columnIndex) {
		this.index = columnIndex;
	}
	
	public int getIndex() {
		return this.index;
	}
	
}
