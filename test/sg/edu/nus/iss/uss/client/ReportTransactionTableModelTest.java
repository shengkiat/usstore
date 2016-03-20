package sg.edu.nus.iss.uss.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.model.TestReportTransactionBuilder;

import static org.junit.Assert.*;

public class ReportTransactionTableModelTest {
	
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
		assertEquals(7, tableModel.getColumnCount());
	}

}
