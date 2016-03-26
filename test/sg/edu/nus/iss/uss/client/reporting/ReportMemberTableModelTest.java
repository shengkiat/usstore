package sg.edu.nus.iss.uss.client.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.TestMemberBuilder;

public class ReportMemberTableModelTest {
	
private static final int EXPECTED_COLUMN_COUNT = 3;
	
	@Test
	public void testGetRowCountShouldBeCorrect() {
		List<Member> members = new ArrayList<>();
		members.add(new TestMemberBuilder().build());
		members.add(new TestMemberBuilder().build());
		
		ReportMemberTableModel tableModel = new ReportMemberTableModel();
		tableModel.updateData(members);
		
		assertEquals(members.size(), tableModel.getRowCount());
	}
	
	@Test
	public void testGetColumnCountShouldBeCorrect() {
		ReportMemberTableModel tableModel = new ReportMemberTableModel();
		assertEquals(EXPECTED_COLUMN_COUNT, tableModel.getColumnCount());
	}
	
	@Test
	public void testGetValueAtShouldBeCorrect() {
		List<Member> members = new ArrayList<>();
		members.add(new TestMemberBuilder().build());
		members.add(new TestMemberBuilder().build());
		
		ReportMemberTableModel tableModel = new ReportMemberTableModel();
		tableModel.updateData(members);
		
		for(int row = 0; row<members.size(); row++) {
			for(int column = 0; column<EXPECTED_COLUMN_COUNT; column++) {
				assertNotNull(tableModel.getValueAt(row, column));
			}
		}
		
	}
	
}
