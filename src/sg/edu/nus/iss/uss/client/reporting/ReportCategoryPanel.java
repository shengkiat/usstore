package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.service.IReportingService;

public class ReportCategoryPanel extends JPanel {

	private static final long serialVersionUID = -3922465380762345397L;
	private ReportCategoryTable rmTable;

	public ReportCategoryPanel(JFrame frame, final IReportingService reportingService) {
		setBounds(800, 800, 200, 100);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel mPanel = new JPanel();
		this.add(mPanel);

		List<Category> categories = null;
		try {
			categories = reportingService.retrieveCategories();
		} catch (UssException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}

		rmTable = new ReportCategoryTable();
		ReportCategoryTableModel tableModel = (ReportCategoryTableModel) rmTable.getModel();
		tableModel.updateData(categories);
		JScrollPane scrollPane = new JScrollPane(rmTable);
		this.add(scrollPane, BorderLayout.CENTER);
	}

}