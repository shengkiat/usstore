package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.service.IReportingService;

final class ReportMemberPanel extends JPanel {

	private static final long serialVersionUID = 3892049231369017101L;
	
	private ReportMemberTable rmTable;
	
	public ReportMemberPanel(JFrame frame, final IReportingService reportingService) {
		setBounds(800, 800, 200, 100);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel mPanel = new JPanel();
		this.add(mPanel);
		
		List<Member> members = null;
		try {
			members = reportingService.retrieveMembers();
		} catch (UssException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "",
			        JOptionPane.ERROR_MESSAGE);
		}
		
		rmTable = new ReportMemberTable();
		ReportMemberTableModel tableModel = (ReportMemberTableModel) rmTable.getModel();
		tableModel.updateData(members);
		JScrollPane scrollPane = new JScrollPane(rmTable);
		this.add( scrollPane, BorderLayout.CENTER );
	}
	

}
