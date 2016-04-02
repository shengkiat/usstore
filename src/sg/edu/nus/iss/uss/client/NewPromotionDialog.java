package sg.edu.nus.iss.uss.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
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

	public NewPromotionDialog(final IDiscountService discountService) {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
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
		}
		{
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
		}
		{
			JLabel lblStartDate = new JLabel("Start Date(yyyy-mm-dd):");
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
		}
		{
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
		}
		{
			JLabel lblDiscount = new JLabel("Discount Percentage:");
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
		}
		{
			final JLabel lblInfo = new JLabel("");
			GridBagConstraints gbc_lblInfo = new GridBagConstraints();
			gbc_lblInfo.anchor = GridBagConstraints.WEST;
			gbc_lblInfo.insets = new Insets(0, 0, 5, 0);
			gbc_lblInfo.gridx = 3;
			gbc_lblInfo.gridy = 8;
			getContentPane().add(lblInfo, gbc_lblInfo);

			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 3;
			gbc_panel.gridy = 11;
			getContentPane().add(panel, gbc_panel);
			
			JButton btnAddProduct = new JButton("Add Promotion");
			btnAddProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String discountCode = textField.getText();
					String discountDescription = textField_1.getText();
					String discountPercentage = textField_4.getText();
					String startDate = textField_2.getText();
					String discountDays = textField_3.getText();
					if(discountCode.equals("")){
						JOptionPane.showMessageDialog(NewPromotionDialog.this, "Please enter Discount Code!" , "New Promotion",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(discountDescription.equals("")){
						JOptionPane.showMessageDialog(NewPromotionDialog.this, "Please enter Discount Description!" , "New Promotion",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(startDate.equals("")){
						JOptionPane.showMessageDialog(NewPromotionDialog.this, "Please enter a Start Date!" , "New Promotion",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(discountDays.equals("")){
						JOptionPane.showMessageDialog(NewPromotionDialog.this, "Please enter Discount Days!" , "New Promotion",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(discountPercentage.equals("")){
						JOptionPane.showMessageDialog(NewPromotionDialog.this, "Please enter Discount Percentage!" , "New Promotion",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					try {
						discountService.addNewDiscount(discountCode, discountDescription, Double.parseDouble(discountPercentage), 
								UssCommonUtil.convertStringToDate(startDate), Integer.parseInt(discountDays));
						lblInfo.setText("Promotion " + discountCode + " is added successfully.");
						lblInfo.setForeground(Color.black);

						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
					}catch(UssException e1) {
						lblInfo.setText("Fail to add promotion: " + discountCode + " - " + e1.getMessage());
						lblInfo.setForeground(Color.RED);
					}
				}
			});
			panel.add(btnAddProduct);
			
			JButton btnCancel = new JButton("Close");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			panel.add(btnCancel);
		}
	}
}
