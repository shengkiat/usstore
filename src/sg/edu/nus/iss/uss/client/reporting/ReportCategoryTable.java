package sg.edu.nus.iss.uss.client.reporting;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class ReportCategoryTable extends JTable {

	private static final long serialVersionUID = -5762666513054901954L;

	public ReportCategoryTable() {
		super(new ReportCategoryTableModel());
		
		adjustColumnWidth();
	}
	
	private void adjustColumnWidth() {
		TableColumn memberNameCol = getColumnModel().getColumn(ReportCategoryColumn.COLUMN_CATEGORY_ID.getIndex());
		memberNameCol.setPreferredWidth(20);
		
		TableColumn memberIDCol = getColumnModel().getColumn(ReportCategoryColumn.COLUMN_CATEGORY_NAME.getIndex());
		memberIDCol.setPreferredWidth(20);

	}
	
	
}