package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.service.IReportingService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class ReportTransactionPanel extends JPanel {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	private JTextField textFieldStartDate;
	private JTextField textFieldEndDate;
	
	private JTable searchResult;
	
	public ReportTransactionPanel(JFrame frame, IReportingService reportingService) {
		setBounds(800, 800, 200, 100);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel searchPanel = new JPanel();
		this.add(searchPanel);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		searchPanel.add(lblStartDate);
		
		textFieldStartDate = new JTextField();
		searchPanel.add(textFieldStartDate);
		textFieldStartDate.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		searchPanel.add(lblEndDate);
		
		textFieldEndDate = new JTextField();
		searchPanel.add(textFieldEndDate);
		textFieldEndDate.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String startDateStr = textFieldEndDate.getText();
				String endDateStr = textFieldEndDate.getText();
				
				if (startDateStr == null || endDateStr == null) {
					
				}
				
				else {
					try {
						Date startDate = UssCommonUtil.convertStringToDate(startDateStr);
						Date endDate = UssCommonUtil.convertStringToDate(endDateStr);
						
						List<ReportTransaction> reportTransactions = reportingService.retrieveReportTransactions(startDate, endDate);
						
						ReportTransactionTableModel tableModel = (ReportTransactionTableModel) searchResult.getModel();
						tableModel.updateData(reportTransactions);
						
					} 
					
					catch (UssException e1) {
						
					}
				}
				
			}
		});
		searchPanel.add(btnSearch);
		
		searchResult = new JTable(new ReportTransactionTableModel(new ArrayList<ReportTransaction>()));
		
		JScrollPane scrollPane = new JScrollPane(searchResult);
		this.add( scrollPane, BorderLayout.CENTER );
		
	}

}
