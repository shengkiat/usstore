package sg.edu.nus.iss.uss.client.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.TestProductBuilder;

public class ReportProductTableModelTest {
	
private static final int EXPECTED_COLUMN_COUNT = 9;
	
	@Test
	public void testGetRowCountShouldBeCorrect() {
		List<Product> products = new ArrayList<>();
		products.add(new TestProductBuilder().build());
		products.add(new TestProductBuilder().build());
		
		ReportProductTableModel tableModel = new ReportProductTableModel();
		tableModel.updateData(products);
		
		assertEquals(products.size(), tableModel.getRowCount());
	}
	
	@Test
	public void testGetColumnCountShouldBeCorrect() {
		ReportProductTableModel tableModel = new ReportProductTableModel();
		assertEquals(EXPECTED_COLUMN_COUNT, tableModel.getColumnCount());
	}
	
	@Test
	public void testGetValueAtShouldBeCorrect() {
		List<Product> products = new ArrayList<>();
		products.add(new TestProductBuilder().build());
		products.add(new TestProductBuilder().build());
		
		ReportProductTableModel tableModel = new ReportProductTableModel();
		tableModel.updateData(products);
		
		for(int row = 0; row<products.size(); row++) {
			for(int column = 0; column<EXPECTED_COLUMN_COUNT; column++) {
				assertNotNull(tableModel.getValueAt(row, column));
			}
		}
		
	}
	
}
