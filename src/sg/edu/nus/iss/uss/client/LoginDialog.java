package sg.edu.nus.iss.uss.client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import sg.edu.nus.iss.uss.service.IAuthorisedService;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 8686021432857020138L;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private boolean succeeded;

	public LoginDialog(Frame parent, final IAuthorisedService authService) {
		super(parent, "Login", true);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		lbUsername = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (authService.isAuthorised(getUsername(), getPassword())) {
					JOptionPane.showMessageDialog(LoginDialog.this, "Hi "
							+ getUsername()
							+ "! You have successfully logged in.", "Login",
							JOptionPane.INFORMATION_MESSAGE);
					succeeded = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this,
							"Invalid username or password", "Login",
							JOptionPane.ERROR_MESSAGE);
					// reset username and password
					tfUsername.setText("");
					pfPassword.setText("");
					succeeded = false;

				}
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
                System.exit(0);
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowOpened(WindowEvent e) {
            }
        });
		
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnCancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	public String getUsername() {
		return tfUsername.getText().trim();
	}

	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	public boolean isSucceeded() {
		return succeeded;
	}
}