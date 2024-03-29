package sg.edu.nus.iss.uss.client.reporting;

import sg.edu.nus.iss.uss.model.Product;

final class ReportProductTableModel extends ReportTableModel<Product> {
	
	private static final long serialVersionUID = -1504901092289179223L;

	private static final String[] COLUMN_NAMES = { "Product ID", "Name", "Brief Description", "Quantity Available",
			"Price", "BarCode Number", "Reorder Quantity", "OrderQuantity", "Product Number"};

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	@Override
	protected String[] toArray(Product product) {
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
	
}
