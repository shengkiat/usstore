package sg.edu.nus.iss.uss.client.reporting;

enum ReportMemberColumn {
	COLUMN_MEMBER_NAME(0),
	COLUMN_MEMBER_ID(1),
	COLUMN_LOYALTY_POINT(2);
	
	private final int index;
	
	private ReportMemberColumn(int columnIndex) {
		this.index = columnIndex;
	}
	
	public int getIndex() {
		return this.index;
	}
}
