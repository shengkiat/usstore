package sg.edu.nus.iss.uss.client.reporting;

enum ReportProductColumn {
	COLUMN_PRODUCT_ID(0),
	COLUMN_PRODUCT_NAME(1),
	COLUMN_BRIEF_DESCRIPTION(2),
	COLUMN_QUANTITY_AVAILABLE(3),
	COLUMN_PRICE(4),
	COLUMN_BARCODE_NUMBER(5),
	COLUMN_REORDER_QUANTITY(6),
	COLUMN_ORDER_QUANTITY(7),
	COLUMN_PRODUCT_NO(8);
	
	private final int index;
	
	private ReportProductColumn(int columnIndex) {
		this.index = columnIndex;
	}
	
	public int getIndex() {
		return this.index;
	}
}
