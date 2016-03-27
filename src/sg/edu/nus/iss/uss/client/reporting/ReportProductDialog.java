package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import sg.edu.nus.iss.uss.service.IProductService;

public final class ReportProductDialog extends JDialog {

	private static final long serialVersionUID = 2190040928549826278L;
	private final ReportProductPanel reportProductPanel;
	
	public ReportProductDialog(JFrame frame, final IProductService productService){
		reportProductPanel = new ReportProductPanel(frame, productService);
		
		setBounds(100, 100, 850, 420);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(reportProductPanel, BorderLayout.CENTER);
		
	}
		
}
