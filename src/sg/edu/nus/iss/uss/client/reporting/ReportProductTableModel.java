package sg.edu.nus.iss.uss.client.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.uss.model.Product;

final class ReportProductTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -1504901092289179223L;

	private static final String[] COLUMN_NAMES = { "Product ID", "Name", "Brief Description", "Quantity Available",
			"Price", "BarCode Number", "Reorder Quantity", "OrderQuantity", "Product Number"};
	
	private List<Product> products;
	
	public ReportProductTableModel() {
		this.products = new ArrayList<Product>();
	}
	
	@Override
	public boolean isCellEditable(int row, int column){  
        return false;  
    }

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return products.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Product product = products.get(row);
		return toArray(product)[column];
	}
	
	
	private String[] toArray(Product product) {
		String[] result = new String[getColumnCount()];
		
		result[ReportProductColumn.COLUMN_PRODUCT_ID.getIndex()] = "" + product.getProductID();
		result[ReportProductColumn.COLUMN_PRODUCT_NAME.getIndex()] = "" + product.getName();
		result[ReportProductColumn.COLUMN_BRIEF_DESCRIPTION.getIndex()] = "" + product.getBriefDescription();
		result[ReportProductColumn.COLUMN_QUANTITY_AVAILABLE.getIndex()] = "" + product.getQuantityAvailable();
		result[ReportProductColumn.COLUMN_PRICE.getIndex()] = "" + product.getPrice();
		result[ReportProductColumn.COLUMN_BARCODE_NUMBER.getIndex()] = "" + product.getBarCodeNumber();
		result[ReportProductColumn.COLUMN_REORDER_QUANTITY.getIndex()] = "" + product.getReorderQuantity();
		result[ReportProductColumn.COLUMN_ORDER_QUANTITY.getIndex()] = "" + product.getOrderQuantity();
		result[ReportProductColumn.COLUMN_PRODUCT_NO.getIndex()] = "" + product.getProductNo();
		
		return result;
	}
	
	void updateData(List<Product> products) {
		this.products = products;
		fireTableDataChanged();
	}
}
