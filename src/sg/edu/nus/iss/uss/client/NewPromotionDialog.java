package sg.edu.nus.iss.uss.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.service.IDiscountService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class NewPromotionDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				try {
					NewPromotionDialog dialog = new NewPromotionDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			//}
		//});
	}*/

	/**
	 * Create the dialog.
	 */
	public NewPromotionDialog(final IDiscountService discountService) {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblCode = new JLabel("Discount Code:");
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.EAST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 2;
		gbc_lblCode.gridy = 2;
		getContentPane().add(lblCode, gbc_lblCode);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 2;
		gbc_lblDescription.gridy = 3;
		getContentPane().add(lblDescription, gbc_lblDescription);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		getContentPane().add(textField_1, gbc_textField_1);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.EAST;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 2;
		gbc_lblStartDate.gridy = 4;
		getContentPane().add(lblStartDate, gbc_lblStartDate);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 4;
		getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDiscountDays = new JLabel("Discount Days:");
		GridBagConstraints gbc_lblDiscountDays = new GridBagConstraints();
		gbc_lblDiscountDays.anchor = GridBagConstraints.EAST;
		gbc_lblDiscountDays.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscountDays.gridx = 2;
		gbc_lblDiscountDays.gridy = 5;
		getContentPane().add(lblDiscountDays, gbc_lblDiscountDays);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 5;
		getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount percentage:");
		GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
		gbc_lblDiscount.anchor = GridBagConstraints.EAST;
		gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscount.gridx = 2;
		gbc_lblDiscount.gridy = 6;
		getContentPane().add(lblDiscount, gbc_lblDiscount);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 6;
		getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 11;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnAddProduct = new JButton("Add Promotion");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					discountService.addNewDiscount(textField.getText(), textField_1.getText(), Double.parseDouble(textField_4.getText()), UssCommonUtil.convertStringToDate(textField_2.getText()), Integer.parseInt(textField_3.getText()));
				}catch(UssException e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnAddProduct);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel);
	}

}
