package sg.edu.nus.iss.uss.client.reporting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.service.IReportingService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class ReportTransactionPanel extends JPanel {
	
	private static final long serialVersionUID = -3360812732250813553L;
	
	private JFormattedTextField textFieldStartDate;
	private JFormattedTextField textFieldEndDate;
	
	private JTable searchResult;
	
	public ReportTransactionPanel(JFrame frame, final IReportingService reportingService) {
		setBounds(800, 800, 200, 100);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel searchPanel = new JPanel();
		this.add(searchPanel);
		
		JLabel lblStartDate = new JLabel("Start Date(" + UssCommonUtil.DATE_FORMAT + "):");
		searchPanel.add(lblStartDate);
		
		textFieldStartDate = new JFormattedTextField(UssCommonUtil.getDefaultDateFormatter());
		searchPanel.add(textFieldStartDate);
		textFieldStartDate.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date(" + UssCommonUtil.DATE_FORMAT + "):");
		searchPanel.add(lblEndDate);
		
		textFieldEndDate = new JFormattedTextField(UssCommonUtil.getDefaultDateFormatter());
		searchPanel.add(textFieldEndDate);
		textFieldEndDate.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String startDateStr = textFieldStartDate.getText();
				String endDateStr = textFieldEndDate.getText();
				
				if ("".equals(startDateStr) || "".equals(endDateStr)) {
					JOptionPane.showMessageDialog(new JFrame(), ErrorConstants.REQUIRED_START_END_DATE, "",
					        JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					try {
						Date startDate = UssCommonUtil.convertStringToDate(startDateStr);
						Date endDate = UssCommonUtil.convertStringToDate(endDateStr);
						
						List<ReportTransaction> reportTransactions = reportingService.retrieveReportTransactions(startDate, endDate);
						
						ReportTransactionTableModel tableModel = (ReportTransactionTableModel) searchResult.getModel();
						tableModel.updateData(reportTransactions);
						
						if (reportTransactions.isEmpty()) {
							JOptionPane.showMessageDialog(new JFrame(), "No records found", "",
							        JOptionPane.INFORMATION_MESSAGE);
						}
						
					} 
					
					catch (UssException e1) {
						JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "",
						        JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		searchPanel.add(btnSearch);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldStartDate.setText("");
				textFieldEndDate.setText("");
				
				ReportTransactionTableModel tableModel = (ReportTransactionTableModel) searchResult.getModel();
				tableModel.updateData(new ArrayList<ReportTransaction>());
				
			}
		});
		searchPanel.add(btnReset);
		
		searchResult = new JTable(new ReportTransactionTableModel(new ArrayList<ReportTransaction>()));
		
		JScrollPane scrollPane = new JScrollPane(searchResult);
		this.add( scrollPane, BorderLayout.CENTER );
		
	}

}
