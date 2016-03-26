package sg.edu.nus.iss.uss.client.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.uss.model.Category;

public class ReportCategoryTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 5292609863882879785L;

	private static final String[] COLUMN_NAMES = { "Category ID", "Category Name"};

	private List<Category> categorys;
	
	public ReportCategoryTableModel() {
		this.categorys = new ArrayList<Category>();
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
		return categorys.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Category category = categorys.get(row);
		return toArray(category)[column];
	}
	
	
	private String[] toArray(Category category) {
		String[] result = new String[getColumnCount()];
		
		result[ReportCategoryColumn.COLUMN_CATEGORY_ID.getIndex()] = "" + category.getCode();
		result[ReportCategoryColumn.COLUMN_CATEGORY_NAME.getIndex()] = "" + category.getName();

		
		return result;
	}
	
	void updateData(List<Category> categorys) {
		this.categorys = categorys;
		fireTableDataChanged();
	}
}