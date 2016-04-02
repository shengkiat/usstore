package sg.edu.nus.iss.uss.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.service.IDiscountService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class UpdateDiscountDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private	JComboBox<String> cbxDiscount= new JComboBox<String>();
	private IDiscountService discountService;
	private JTextField txtDescription;
	private JTextField txtDiscountPercent;
	private JTextField txtStartDate;
	private JTextField txtDiscountDays;
	private String discountCode;
	private static final int FIELD_DISCOUNT_CODE = 0;
	private static final int FIELD_DISCOUNT_DESCRIPTION = 1;
	private static final int FIELD_DISCOUNT_START_DATE = 2;
	private static final int FIELD_DISCOUNT_PERIOD = 3;
	private static final int FIELD_DISCOUNT_PERCENTAGE = 4;
	private static final int FIELD_DISCOUNT_APPLICABLE = 5;
	
	private static final int TOTAL_FIELDS = 6;


	/**
	 * Create the dialog.
	 */
	public UpdateDiscountDialog(final IDiscountService discountService) {
		
		this.discountService = discountService;
		
		List<Discount> discounts = this.discountService.getAll();
		for(Discount d : discounts) {
			this.cbxDiscount.addItem(Arrays.toString(discountToStrArray(d)));
		}
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblDiscountName = new JLabel("Discount Name:");
			GridBagConstraints gbc_lblDiscountName = new GridBagConstraints();
			gbc_lblDiscountName.insets = new Insets(0, 0, 5, 5);
			gbc_lblDiscountName.anchor = GridBagConstraints.EAST;
			gbc_lblDiscountName.gridx = 2;
			gbc_lblDiscountName.gridy = 1;
			contentPanel.add(lblDiscountName, gbc_lblDiscountName);
		}
		{
			GridBagConstraints gbc_cbxDiscount = new GridBagConstraints();
			gbc_cbxDiscount.insets = new Insets(0, 0, 5, 0);
			gbc_cbxDiscount.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxDiscount.gridx = 3;
			gbc_cbxDiscount.gridy = 1;
			cbxDiscount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dicountString = (String) cbxDiscount.getSelectedItem();
					String[] dicountEntry = dicountString.split(", ");
					discountCode = dicountEntry[FIELD_DISCOUNT_CODE].replace("[", "");
					txtDescription.setText(dicountEntry[FIELD_DISCOUNT_DESCRIPTION]);
					txtDiscountPercent.setText(dicountEntry[FIELD_DISCOUNT_PERCENTAGE]);
					txtStartDate.setText(dicountEntry[FIELD_DISCOUNT_START_DATE]);
					if(txtStartDate.getText().contains("ALWAYS"))
						txtStartDate.setEnabled(false);
					else
						txtStartDate.setEnabled(true);
					txtDiscountDays.setText("" + dicountEntry[FIELD_DISCOUNT_PERIOD]);
					if(txtDiscountDays.getText().contains("ALWAYS"))
						txtDiscountDays.setEnabled(false);
					else
						txtDiscountDays.setEnabled(true);
				}
			});
			contentPanel.add(cbxDiscount, gbc_cbxDiscount);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.anchor = GridBagConstraints.EAST;
			gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescription.gridx = 2;
			gbc_lblDescription.gridy = 2;
			contentPanel.add(lblDescription, gbc_lblDescription);
		}
		{
			txtDescription = new JTextField();
			GridBagConstraints gbc_txtDescription = new GridBagConstraints();
			gbc_txtDescription.insets = new Insets(0, 0, 5, 0);
			gbc_txtDescription.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDescription.gridx = 3;
			gbc_txtDescription.gridy = 2;
			contentPanel.add(txtDescription, gbc_txtDescription);
			txtDescription.setColumns(10);
		}
		{
			JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
			GridBagConstraints gbc_lblDiscountPercentage = new GridBagConstraints();
			gbc_lblDiscountPercentage.anchor = GridBagConstraints.EAST;
			gbc_lblDiscountPercentage.insets = new Insets(0, 0, 5, 5);
			gbc_lblDiscountPercentage.gridx = 2;
			gbc_lblDiscountPercentage.gridy = 3;
			contentPanel.add(lblDiscountPercentage, gbc_lblDiscountPercentage);
		}
		{
			txtDiscountPercent = new JTextField();
			GridBagConstraints gbc_txtDiscountPercent = new GridBagConstraints();
			gbc_txtDiscountPercent.insets = new Insets(0, 0, 5, 0);
			gbc_txtDiscountPercent.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDiscountPercent.gridx = 3;
			gbc_txtDiscountPercent.gridy = 3;
			contentPanel.add(txtDiscountPercent, gbc_txtDiscountPercent);
			txtDiscountPercent.setColumns(10);
		}
		{
			JLabel lblDate = new JLabel("Start Date(yyyy-mm-dd):");
			GridBagConstraints gbc_lblDate = new GridBagConstraints();
			gbc_lblDate.anchor = GridBagConstraints.EAST;
			gbc_lblDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblDate.gridx = 2;
			gbc_lblDate.gridy = 4;
			contentPanel.add(lblDate, gbc_lblDate);
		}
		{
			txtStartDate = new JTextField();
			GridBagConstraints gbc_txtStartDate = new GridBagConstraints();
			gbc_txtStartDate.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartDate.gridx = 3;
			gbc_txtStartDate.gridy = 4;
			contentPanel.add(txtStartDate, gbc_txtStartDate);
			txtStartDate.setColumns(10);
		}
		{
			JLabel lblDiscountDays = new JLabel("Discount Days:");
			GridBagConstraints gbc_lblDiscountDays = new GridBagConstraints();
			gbc_lblDiscountDays.anchor = GridBagConstraints.EAST;
			gbc_lblDiscountDays.insets = new Insets(0, 0, 0, 5);
			gbc_lblDiscountDays.gridx = 2;
			gbc_lblDiscountDays.gridy = 5;
			contentPanel.add(lblDiscountDays, gbc_lblDiscountDays);
		}
		{
			txtDiscountDays = new JTextField();
			GridBagConstraints gbc_txtDiscountDays = new GridBagConstraints();
			gbc_txtDiscountDays.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDiscountDays.gridx = 3;
			gbc_txtDiscountDays.gridy = 5;
			contentPanel.add(txtDiscountDays, gbc_txtDiscountDays);
			txtDiscountDays.setColumns(10);
		}
		{
			final JLabel lblInfo = new JLabel("");
			GridBagConstraints gbc_lblInfo = new GridBagConstraints();
			gbc_lblInfo.anchor = GridBagConstraints.WEST;
			gbc_lblInfo.insets = new Insets(0, 0, 0, 5);
			gbc_lblInfo.gridx = 3;
			gbc_lblInfo.gridy = 7;
			contentPanel.add(lblInfo, gbc_lblInfo);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String discountDescription = txtDescription.getText();
						String discountPercentage = txtDiscountPercent.getText();
						String startDate = txtStartDate.getText();
						String discountDays = txtDiscountDays.getText();
						if(discountDescription.equals("")){
							JOptionPane.showMessageDialog(UpdateDiscountDialog.this, "Please enter Discount Description!" , "Update Promotion",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(startDate.equals("")){
							JOptionPane.showMessageDialog(UpdateDiscountDialog.this, "Please enter a Start Date!" , "Update Promotion",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(discountDays.equals("")){
							JOptionPane.showMessageDialog(UpdateDiscountDialog.this, "Please enter Discount Days!" , "Update Promotion",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(discountPercentage.equals("")){
							JOptionPane.showMessageDialog(UpdateDiscountDialog.this, "Please enter Discount Percentage!" , "Update Promotion",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(!discountDays.matches("\\d+")) {
							JOptionPane.showMessageDialog(UpdateDiscountDialog.this, "Discount Days must be a number!" , "New Promotion",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						try {
							discountService.updateDiscount(discountCode, discountDescription, Double.parseDouble(discountPercentage), 
									startDate, discountDays);
							lblInfo.setText("Promotion is updated successfully.");
							lblInfo.setForeground(Color.black);
							//List<Discount> discounts = discountService.getAll();
							//cbxDiscount.removeAllItems();
							//for(Discount d : discounts) {
								//cbxDiscount.addItem(Arrays.toString(discountToStrArray(d)));
							//}
						}catch(UssException e1) {
							lblInfo.setText("Fail to update promotion: " + e1.getMessage());
							lblInfo.setForeground(Color.RED);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		this.cbxDiscount.setSelectedIndex(0);	
	}
	
	private String[] discountToStrArray(Discount discount){
		String[] arr = new String[TOTAL_FIELDS];
		if(discount instanceof MemberOnlyDiscount) {
			MemberOnlyDiscount moDiscount = (MemberOnlyDiscount)discount;
			arr[FIELD_DISCOUNT_CODE] = moDiscount.getDiscountCode();
			arr[FIELD_DISCOUNT_DESCRIPTION] = "" + moDiscount.getDescription();
			arr[FIELD_DISCOUNT_START_DATE] = "" + "ALWAYS";
			arr[FIELD_DISCOUNT_PERIOD] = "" + "ALWAYS";
			arr[FIELD_DISCOUNT_PERCENTAGE] = "" + moDiscount.getDiscountPercentage();
			arr[FIELD_DISCOUNT_APPLICABLE] = "" + "Member";
		}
		if(discount instanceof DaySpecialDiscount) {
			DaySpecialDiscount dsDiscount = (DaySpecialDiscount)discount;
			arr[FIELD_DISCOUNT_CODE] = dsDiscount.getDiscountCode();
			arr[FIELD_DISCOUNT_DESCRIPTION] = "" + dsDiscount.getDescription();
			arr[FIELD_DISCOUNT_START_DATE] = "" + UssCommonUtil.convertDateToString(dsDiscount.getStartDate());
			arr[FIELD_DISCOUNT_PERIOD] = "" + dsDiscount.getDiscountDays();
			arr[FIELD_DISCOUNT_PERCENTAGE] = "" + dsDiscount.getDiscountPercentage();
			arr[FIELD_DISCOUNT_APPLICABLE] = "" + "All";
		}
		return arr;
	}
}
