package sg.edu.nus.iss.uss.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.model.TestReportTransactionBuilder;

import static org.junit.Assert.*;

public class ReportTransactionTableModelTest {
	
	private static final int EXPECTED_COLUMN_COUNT = 7;
	
	@Test
	public void testGetRowCountShouldBeCorrect() {
		List<ReportTransaction> reportTransactions = new ArrayList<>();
		reportTransactions.add(new TestReportTransactionBuilder().build());
		reportTransactions.add(new TestReportTransactionBuilder().build());
		
		ReportTransactionTableModel tableModel = new ReportTransactionTableModel(reportTransactions);
		assertEquals(reportTransactions.size(), tableModel.getRowCount());
	}
	
	@Test
	public void testGetColumnCountShouldBeCorrect() {
		ReportTransactionTableModel tableModel = new ReportTransactionTableModel(new ArrayList<ReportTransaction>());
		assertEquals(EXPECTED_COLUMN_COUNT, tableModel.getColumnCount());
	}
	
	@Test
	public void testGetValueAtShouldBeCorrect() {
		List<ReportTransaction> reportTransactions = new ArrayList<>();
		reportTransactions.add(new TestReportTransactionBuilder().build());
		reportTransactions.add(new TestReportTransactionBuilder().build());
		
		ReportTransactionTableModel tableModel = new ReportTransactionTableModel(reportTransactions);
		
		for(int row = 0; row<reportTransactions.size(); row++) {
			for(int column = 0; column<EXPECTED_COLUMN_COUNT; column++) {
				assertNotNull(tableModel.getValueAt(row, column));
			}
		}
		
	}

}
