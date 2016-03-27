package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import sg.edu.nus.iss.uss.service.IReportingService;

public final class ReportMemberDialog extends JDialog {

	private static final long serialVersionUID = 165788297492795395L;
	
	private final ReportMemberPanel reportMemberPanel;
	
	public ReportMemberDialog(JFrame frame, final IReportingService reportingService){
		reportMemberPanel = new ReportMemberPanel(frame, reportingService);
		
		setBounds(100, 100, 750, 420);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(reportMemberPanel, BorderLayout.CENTER);
		
	}
		
}
