package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import sg.edu.nus.iss.uss.service.IReportingService;

public final class ReportCategoryDialog extends JDialog {

	private static final long serialVersionUID = 3305057073596106961L;

	private final ReportCategoryPanel reportCategoryPanel;

	public ReportCategoryDialog(JFrame frame, final IReportingService reportingService) {
		reportCategoryPanel = new ReportCategoryPanel(frame, reportingService);

		setBounds(100, 100, 750, 420);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(reportCategoryPanel, BorderLayout.CENTER);

	}

}
