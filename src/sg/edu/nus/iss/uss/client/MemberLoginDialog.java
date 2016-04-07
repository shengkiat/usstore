package sg.edu.nus.iss.uss.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.uss.service.ICheckOutService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MemberLoginDialog extends JDialog {

	private static final long serialVersionUID = 659506558163113890L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMemberID;

	private boolean _isMember = false;
	private String _memberID;

	/**
	 * Create the dialog.
	 */
	public MemberLoginDialog(final ICheckOutService checkoutService) {
		
		setBounds(100, 100, 450, 120);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Member ID:");
			contentPanel.add(lblNewLabel);
		}
		{
			txtMemberID = new JTextField();
			contentPanel.add(txtMemberID);
			txtMemberID.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						_memberID = txtMemberID.getText();
						
						if (checkoutService.determineMemberID(_memberID)){
							_isMember = true;
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(MemberLoginDialog.this,
									"Invalid Member ID", "Member ID",
									JOptionPane.ERROR_MESSAGE);
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
						MemberLoginDialog.this.setVisible(false);
						MemberLoginDialog.this.dispatchEvent(new WindowEvent(
								MemberLoginDialog.this, WindowEvent.WINDOW_CLOSING));
						
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean isMember() {
		return _isMember;
	}
	
	public String getMemberID() {
		return _memberID;
	}
}
