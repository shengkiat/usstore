package sg.edu.nus.iss.uss.client.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.TestCategoryBuilder;

public class ReportCategoryTableModelTest {
	
private static final int EXPECTED_COLUMN_COUNT = 2;
	
	@Test
	public void testGetRowCountShouldBeCorrect() {
		List<Category> categories = new ArrayList<>();
		categories.add(new TestCategoryBuilder().build());
		categories.add(new TestCategoryBuilder().build());
		
		ReportCategoryTableModel tableModel = new ReportCategoryTableModel();
		tableModel.updateData(categories);
		
		assertEquals(categories.size(), tableModel.getRowCount());
	}
	
	@Test
	public void testGetColumnCountShouldBeCorrect() {
		ReportCategoryTableModel tableModel = new ReportCategoryTableModel();
		assertEquals(EXPECTED_COLUMN_COUNT, tableModel.getColumnCount());
	}
	
	@Test
	public void testGetValueAtShouldBeCorrect() {
		List<Category> categories = new ArrayList<>();
		categories.add(new TestCategoryBuilder().build());
		categories.add(new TestCategoryBuilder().build());
		
		ReportCategoryTableModel tableModel = new ReportCategoryTableModel();
		tableModel.updateData(categories);
		
		for(int row = 0; row<categories.size(); row++) {
			for(int column = 0; column<EXPECTED_COLUMN_COUNT; column++) {
				assertNotNull(tableModel.getValueAt(row, column));
			}
		}
		
	}
	
}
