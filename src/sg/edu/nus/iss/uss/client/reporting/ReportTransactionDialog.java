package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import sg.edu.nus.iss.uss.service.IReportingService;

public final class ReportTransactionDialog extends JDialog {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	private final ReportTransactionPanel reportTransactionPanel;
	
	public ReportTransactionDialog(JFrame frame, final IReportingService reportingService) {
		reportTransactionPanel = new ReportTransactionPanel(this, reportingService);
		
		setBounds(100, 100, 750, 420);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(reportTransactionPanel, BorderLayout.CENTER);
	}

}
