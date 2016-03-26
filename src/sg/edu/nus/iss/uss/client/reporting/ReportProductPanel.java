package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

final class ReportProductPanel extends JPanel {

	private static final long serialVersionUID = -5266857409629921600L;
	private ReportProductTable rpTable;
	
	public ReportProductPanel(JFrame frame, final IProductService productService) {
		setBounds(800, 800, 255, 100);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel mPanel = new JPanel();
		this.add(mPanel);
		
		List<Product> products = null;
		products = productService.retrieveProductList();
		
		rpTable = new ReportProductTable();
		ReportProductTableModel tableModel = (ReportProductTableModel) rpTable.getModel();
		tableModel.updateData(products);
		JScrollPane scrollPane = new JScrollPane(rpTable);
		this.add( scrollPane, BorderLayout.CENTER );
	}
	

}
