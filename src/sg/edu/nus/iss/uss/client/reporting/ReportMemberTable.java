package sg.edu.nus.iss.uss.client.reporting;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class ReportMemberTable extends JTable {

	private static final long serialVersionUID = 3054802726436260964L;
	
	
	public ReportMemberTable() {
		super(new ReportMemberTableModel());
		
		adjustColumnWidth();
	}
	
	private void adjustColumnWidth() {
		TableColumn memberNameCol = getColumnModel().getColumn(ReportMemberColumn.COLUMN_MEMBER_NAME.getIndex());
		memberNameCol.setPreferredWidth(20);
		
		TableColumn memberIDCol = getColumnModel().getColumn(ReportMemberColumn.COLUMN_MEMBER_ID.getIndex());
		memberIDCol.setPreferredWidth(20);
		
		TableColumn loyaltyPointCol = getColumnModel().getColumn(ReportMemberColumn.COLUMN_LOYALTY_POINT.getIndex());
		loyaltyPointCol.setPreferredWidth(20);
	}
	
	
}
