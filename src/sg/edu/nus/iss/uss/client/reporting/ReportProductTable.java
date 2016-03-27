package sg.edu.nus.iss.uss.client.reporting;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

final class ReportProductTable extends JTable {

	private static final long serialVersionUID = 3054802726436260964L;
	
	
	public ReportProductTable() {
		super(new ReportProductTableModel());
		
		adjustColumnWidth();
	}
	
	private void adjustColumnWidth() {
		
		TableColumn productIDCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_PRODUCT_ID.getIndex());
		productIDCol.setPreferredWidth(20);
		
		TableColumn productNameCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_PRODUCT_NAME.getIndex());
		productNameCol.setPreferredWidth(50);
		
		TableColumn briefDescCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_BRIEF_DESCRIPTION.getIndex());
		briefDescCol.setPreferredWidth(100);
		
		TableColumn quantityAvailableCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_QUANTITY_AVAILABLE.getIndex());
		quantityAvailableCol.setPreferredWidth(15);
		
		TableColumn priceCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_PRICE.getIndex());
		priceCol.setPreferredWidth(15);
		
		TableColumn barcodeNoCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_BARCODE_NUMBER.getIndex());
		barcodeNoCol.setPreferredWidth(15);
		
		TableColumn reorderQtyCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_REORDER_QUANTITY.getIndex());
		reorderQtyCol.setPreferredWidth(15);
		
		TableColumn orderQtyCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_ORDER_QUANTITY.getIndex());
		orderQtyCol.setPreferredWidth(15);
		
		TableColumn productNOCol = getColumnModel().getColumn(ReportProductColumn.COLUMN_PRODUCT_NO.getIndex());
		productNOCol.setPreferredWidth(10);

	}
	
	
}
