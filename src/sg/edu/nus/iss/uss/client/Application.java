package sg.edu.nus.iss.uss.client;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application {

	private JFrame frame;
	//private AuthorisedService authService = new AuthorisedService();
	private JTextField textFieldBarcode;
	
	
	
	private JPanel rightClickPaymentPanel;
	
	private JPanel rightMemberPanel ;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
					
					
					

					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
			
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Store Keeper Login
		LoginDialog loginDlg = new LoginDialog(frame);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
        loginDlg.setVisible(true);
        
        loginDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
            
        // if logon successfully
        if(loginDlg.isSucceeded()){
            //btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
        }
		
		
		
		
		
		
		
		
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Member");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New Member");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewMemberDialog newMemberDlg = new NewMemberDialog();
				
				newMemberDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
				newMemberDlg.setVisible(true);
				
				
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenu mnInventory = new JMenu("Inventory");
		menuBar.add(mnInventory);
		
		JMenuItem mntmAddStock = new JMenuItem("Add Stock");
		mnInventory.add(mntmAddStock);
		
		JMenuItem mntmNewProduct = new JMenuItem("New Product");
		mntmNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				NewProductDialog newProductDlg = new NewProductDialog();
				
				newProductDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
				newProductDlg.setVisible(true);
				
				
			}
		});
		mnInventory.add(mntmNewProduct);
		
		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);
		
		JMenuItem mntmNewCategory = new JMenuItem("New Category");
		mntmNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				CategoryDialog categoryDlg = new CategoryDialog();
				
				categoryDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
				categoryDlg.setVisible(true);

				
			}
		});
		mnCategory.add(mntmNewCategory);
		
		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);
		
		JMenuItem mntmCategories = new JMenuItem("Categories");
		mnReporting.add(mntmCategories);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mnReporting.add(mntmProducts);
		
		JMenuItem mntmTransactions = new JMenuItem("Transactions");
		mnReporting.add(mntmTransactions);
		
		JMenuItem mntmMembers = new JMenuItem("Members");
		mnReporting.add(mntmMembers);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(800, 800, 200, 100);
		frame.getContentPane().add(leftPanel);
		
		String[] selections = { "green", "red", "orange", "dark blue" };
		JList list = new JList(selections);
		list.setValueIsAdjusting(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setMaximumSize(new Dimension(500, 200));
		scrollPane.setMinimumSize (new Dimension(500, 200));
		Dimension d = list.getPreferredSize();
		d.width = 200;
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		scrollPane.setPreferredSize(new Dimension(500, 500));
		leftPanel.add(scrollPane);
		
		JPanel barcodePanel = new JPanel();
		leftPanel.add(barcodePanel);
		
		JLabel lblBarcode = new JLabel("BarCode:");
		barcodePanel.add(lblBarcode);
		
		textFieldBarcode = new JTextField();
		barcodePanel.add(textFieldBarcode);
		textFieldBarcode.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		barcodePanel.add(btnAdd);
		
		 rightClickPaymentPanel = new JPanel();
		frame.getContentPane().add(rightClickPaymentPanel);
		rightClickPaymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnFinishAndPay = new JButton("Finish and Pay");
		btnFinishAndPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Finish buying items
				
				frame.remove(rightClickPaymentPanel);
				frame.getContentPane().add(rightMemberPanel);
                frame.revalidate(); // For Java 1.7 or above.
                // frame.getContentPane().validate(); // For Java 1.6 or below.
                frame.repaint();
				
			}
		});
		btnFinishAndPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rightClickPaymentPanel.add(btnFinishAndPay);
		
		rightMemberPanel = new JPanel();

		
		JButton btnMemberPay = new JButton("Member");
		btnMemberPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rightMemberPanel.add(btnMemberPay);
		
		JButton btnNonMemberPay = new JButton("Non Member");
		btnNonMemberPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rightMemberPanel.add(btnNonMemberPay);
	
		

		
		


		
		
	}
	
	
	
	

}
