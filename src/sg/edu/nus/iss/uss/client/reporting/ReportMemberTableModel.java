package sg.edu.nus.iss.uss.client.reporting;

import sg.edu.nus.iss.uss.model.Member;

final class ReportMemberTableModel extends ReportTableModel<Member> {
	
	private static final long serialVersionUID = 5292609863882879785L;

	private static final String[] COLUMN_NAMES = { "Member Name", "Member ID", "Loyalty Point"};

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	@Override
	protected String[] toArray(Member member) {
		String[] result = new String[getColumnCount()];
		
		result[ReportMemberColumn.COLUMN_MEMBER_NAME.getIndex()] = "" + member.getName();
		result[ReportMemberColumn.COLUMN_MEMBER_ID.getIndex()] = "" + member.getMemberID();
		result[ReportMemberColumn.COLUMN_LOYALTY_POINT.getIndex()] = "" + member.getLoyaltyPoint();
		
		return result;
	}
}
