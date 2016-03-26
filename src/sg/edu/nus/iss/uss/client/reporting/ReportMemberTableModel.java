package sg.edu.nus.iss.uss.client.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.uss.model.Member;

public class ReportMemberTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 5292609863882879785L;

	private static final String[] COLUMN_NAMES = { "Member Name", "Member ID", "Loyalty Point"};

	private List<Member> members;
	
	public ReportMemberTableModel() {
		this.members = new ArrayList<Member>();
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
		return members.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Member member = members.get(row);
		return toArray(member)[column];
	}
	
	
	private String[] toArray(Member member) {
		String[] result = new String[getColumnCount()];
		
		result[ReportMemberColumn.COLUMN_MEMBER_NAME.getIndex()] = "" + member.getName();
		result[ReportMemberColumn.COLUMN_MEMBER_ID.getIndex()] = "" + member.getMemberID();
		result[ReportMemberColumn.COLUMN_LOYALTY_POINT.getIndex()] = "" + member.getLoyaltyPoint();
		
		return result;
	}
	
	void updateData(List<Member> members) {
		this.members = members;
		fireTableDataChanged();
	}
}
