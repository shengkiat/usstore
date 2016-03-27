package sg.edu.nus.iss.uss.client.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

abstract class ReportTableModel<T> extends AbstractTableModel {
	
	private static final long serialVersionUID = 5292609863882879785L;
	
	private List<T> records;
	
	public ReportTableModel() {
		this.records = new ArrayList<T>();
	}
	
	@Override
	public boolean isCellEditable(int row, int column){  
        return false;
    }
	
	@Override
	public int getRowCount() {
		return records.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		T record = records.get(row);
		return toArray(record)[column];
	}
	
	protected abstract String[] toArray(T record);
	
	protected void updateData(List<T> records) {
		this.records = records;
		fireTableDataChanged();
	}
}
