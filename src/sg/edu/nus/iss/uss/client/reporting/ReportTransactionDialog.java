package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.uss.service.IReportingService;

public class ReportTransactionDialog extends JDialog {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	private final ReportTransactionPanel reportTransactionPanel;
	
	public ReportTransactionDialog(JFrame frame, final IReportingService reportingService) {
		reportTransactionPanel = new ReportTransactionPanel(frame, reportingService);
		
		setBounds(100, 100, 750, 420);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(reportTransactionPanel, BorderLayout.CENTER);
	}

}
