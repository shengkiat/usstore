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
import sg.edu.nus.iss.uss.service.IMemberService;

public class NewMemberDialog extends JDialog {

	private static final long serialVersionUID = -62814988236609858L;

	private JTextField txtMemberID;
	private JTextField txtName;

	/**
	 * Create the dialog.
	 */
	public NewMemberDialog(final IMemberService memberService) {

		setBounds(100, 100, 450, 180);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblStudentstaffId = new JLabel("Student/Staff ID:");
		GridBagConstraints gbc_lblStudentstaffId = new GridBagConstraints();
		gbc_lblStudentstaffId.anchor = GridBagConstraints.EAST;
		gbc_lblStudentstaffId.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentstaffId.gridx = 2;
		gbc_lblStudentstaffId.gridy = 1;
		getContentPane().add(lblStudentstaffId, gbc_lblStudentstaffId);

		txtMemberID = new JTextField();
		GridBagConstraints gbc_txtMemberID = new GridBagConstraints();
		gbc_txtMemberID.insets = new Insets(0, 0, 5, 0);
		gbc_txtMemberID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMemberID.gridx = 3;
		gbc_txtMemberID.gridy = 1;
		getContentPane().add(txtMemberID, gbc_txtMemberID);
		txtMemberID.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 2;
		getContentPane().add(lblName, gbc_lblName);

		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 3;
		gbc_txtName.gridy = 2;
		getContentPane().add(txtName, gbc_txtName);
		txtName.setColumns(10);

		final JLabel lblInfo = new JLabel("");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.anchor = GridBagConstraints.WEST;
		gbc_lblInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblInfo.gridx = 3;
		gbc_lblInfo.gridy = 3;
		getContentPane().add(lblInfo, gbc_lblInfo);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);

		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String memberID = txtMemberID.getText();
				String memberName = txtName.getText();

				if (memberID.equals("")){
					JOptionPane.showMessageDialog(NewMemberDialog.this, "Please enter Member ID" , "New Member",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(memberName.equals("")){
					JOptionPane.showMessageDialog(NewMemberDialog.this, "Please enter Member Name" , "New Member",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				
				try {
					memberService.registerNewMember(memberName, memberID);

					lblInfo.setText(memberName + "is added successfully.");
					lblInfo.setForeground(Color.black);

					txtMemberID.setText("");
					txtName.setText("");

				} catch (UssException e1) {
					lblInfo.setText("Fail to add member: " + memberName);
					lblInfo.setForeground(Color.RED);
				}
			}
		});
		panel.add(btnAddMember);

		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel);

	}

}
