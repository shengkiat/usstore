package sg.edu.nus.iss.uss.client.reporting;

enum ReportCategoryColumn {
	COLUMN_CATEGORY_ID(0),
	COLUMN_CATEGORY_NAME(1);
	
	private final int index;
	
	private ReportCategoryColumn(int columnIndex) {
		this.index = columnIndex;
	}
	
	public int getIndex() {
		return this.index;
	}
}
