package sg.edu.nus.iss.uss.client.reporting;

import java.util.List;

import javax.swing.table.AbstractTableModel;

abstract class ReportTableModel<T> extends AbstractTableModel {
	
	private static final long serialVersionUID = 5292609863882879785L;
	
	@Override
	public boolean isCellEditable(int row, int column){  
        return false;  
    }
	
	protected abstract String[] toArray(T record);
	
	protected abstract void updateData(List<T> records);
}
