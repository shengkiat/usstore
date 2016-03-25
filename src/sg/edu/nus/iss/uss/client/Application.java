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
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.FlowLayout;

import javax.swing.JTextField;

import sg.edu.nus.iss.uss.client.reporting.ReportTransactionPanel;
import sg.edu.nus.iss.uss.dao.*;
import sg.edu.nus.iss.uss.dao.filedataaccess.*;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.*;
import sg.edu.nus.iss.uss.service.impl.*;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class Application {

	private JFrame frame;
	private JTextField txtBarcode;

	private JPanel leftPanel, rightEnterAmountPanel;
	private JPanel rightFinishPaymentPanel;
	private JPanel rightMemberPanel;

	private IStoreKeeperDataAccess storeKeeperDAO;
	private IProductDataAccess productDAO;
	private ICategoryDataAccess categoryDAO;
	private	ITransactionDataAccess transactionDAO;
	private IVendorDataAccess vendorDAO;
	
	private IAuthorisedService authService;
	private IReportingService reportingService;
	private IProductService productService;
	private ITransactionService transactionService;
	private	ICategoryService categoryService;
	private IVendorService vendorService;
	
	private List<Product> products;
private double subTotal = 0;
	
	private JTextField txtAmountReceived;
	private JTextField txtMemberDollarRedem;

	private JLabel lbl1, lbl2, lbl3,lblsubTotal;
	private JLabel lblMemberName, lblMemberLoyaltyPts, lblAmountReceived;

	private DefaultTableModel shoppingcart;

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
	 * 
	 * @throws UssException
	 */
	public Application() throws UssException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws UssException
	 */
	private void initialize() throws UssException {

		// Initialize Services
		this.storeKeeperDAO = new StoreKeeperFileDataAccess();
		this.authService = new AuthorisedService(storeKeeperDAO);

		this.transactionDAO = new TransactionFileDataAccess();
		this.transactionService = new TransactionService(transactionDAO);
		

		this.reportingService = new ReportingService(transactionService, productService);


		//this.productDAO = new ProductFileDataAccess();
		this.categoryDAO = new CategoryFileDataAccess();
		this.productService = new ProductService(productDAO);

		// Get the list of products
		//this.products = this.productService.retrieveProductList();

		this.vendorDAO = new VendorFileDataAccess();
		this.vendorService = new VendorService(vendorDAO);
		this.categoryService = new CategoryService(vendorService,categoryDAO);
		

		
		

		// Initialize Table Model use for shopping cart
		shoppingcart = new DefaultTableModel(0, 0);
		// add header of the table
		String header[] = new String[] { "Product", "Price" };

		// add header in table model
		shoppingcart.setColumnIdentifiers(header);

		frame = new JFrame();
		frame.setBounds(100, 100, 950, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Store Keeper Login
		LoginDialog loginDlg = new LoginDialog(frame, authService);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		loginDlg.setVisible(true);

		loginDlg.setModalityType(ModalityType.TOOLKIT_MODAL);

		// if logon successfully
		if (loginDlg.isSucceeded()) {
			// btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
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

				CategoryDialog categoryDlg = new CategoryDialog(categoryService);

				categoryDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
				categoryDlg.setVisible(true);

			}
		});
		mnCategory.add(mntmNewCategory);

		JMenu mnPromotion = new JMenu("Promotions");
		menuBar.add(mnPromotion);

		JMenuItem mntmNewPromotion = new JMenuItem("New Promotion");
		mntmNewPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				NewPromotionDialog promotionDlg = new NewPromotionDialog();

				promotionDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
				promotionDlg.setVisible(true);
			}
		});
		mnPromotion.add(mntmNewPromotion);

		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);

		JMenuItem mntmCategories = new JMenuItem("Categories");
		mnReporting.add(mntmCategories);

		JMenuItem mntmProducts = new JMenuItem("Products");
		mnReporting.add(mntmProducts);

		JMenuItem mntmMembers = new JMenuItem("Members");
		mnReporting.add(mntmMembers);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		leftPanel = new JPanel();
		leftPanel.setBounds(800, 800, 200, 100);
		frame.getContentPane().add(leftPanel);

		JTable tbScanProducts = new JTable();
		tbScanProducts.setModel(shoppingcart);

		// tbScanProducts.setValueIsAdjusting(true);
		tbScanProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(tbScanProducts);
		scrollPane.setMaximumSize(new Dimension(500, 200));
		scrollPane.setMinimumSize(new Dimension(500, 200));
		Dimension d = tbScanProducts.getPreferredSize();
		d.width = 200;
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

		JPanel barcodePanel = new JPanel();
		leftPanel.add(barcodePanel);
		barcodePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblBarcode = new JLabel("BarCode:");
		barcodePanel.add(lblBarcode);

		txtBarcode = new JTextField();
		barcodePanel.add(txtBarcode);
		txtBarcode.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				readBarcode();

			}
		});
		barcodePanel.add(btnAdd);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		leftPanel.add(scrollPane);

		JPanel subtotalPanel = new JPanel();
		leftPanel.add(subtotalPanel);

		JLabel lblTotal = new JLabel("Total:");
		subtotalPanel.add(lblTotal);

		 lblsubTotal = new JLabel("0");
		subtotalPanel.add(lblsubTotal);

		// TODO
		rightEnterAmountPanel = new JPanel();
		// frame.getContentPane().add(rightEnterAmountPanel,
		// BorderLayout.NORTH);
		GridBagLayout gbl_rightEnterAmountPanel = new GridBagLayout();
		gbl_rightEnterAmountPanel.columnWidths = new int[] { 50, 134, 86, 0 };
		gbl_rightEnterAmountPanel.rowHeights = new int[] { 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_rightEnterAmountPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_rightEnterAmountPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		rightEnterAmountPanel.setLayout(gbl_rightEnterAmountPanel);

		lbl1 = new JLabel("Member Name:");
		GridBagConstraints gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.anchor = GridBagConstraints.EAST;
		gbc_lbl1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl1.gridx = 1;
		gbc_lbl1.gridy = 2;
		rightEnterAmountPanel.add(lbl1, gbc_lbl1);

		lblMemberName = new JLabel("New label");
		GridBagConstraints gbc_lblMemberName = new GridBagConstraints();
		gbc_lblMemberName.anchor = GridBagConstraints.WEST;
		gbc_lblMemberName.insets = new Insets(0, 0, 5, 0);
		gbc_lblMemberName.gridx = 2;
		gbc_lblMemberName.gridy = 2;
		rightEnterAmountPanel.add(lblMemberName, gbc_lblMemberName);

		lbl2 = new JLabel("Member Loyalty Dollars:");
		GridBagConstraints gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.anchor = GridBagConstraints.EAST;
		gbc_lbl2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl2.gridx = 1;
		gbc_lbl2.gridy = 3;
		rightEnterAmountPanel.add(lbl2, gbc_lbl2);

		lblMemberLoyaltyPts = new JLabel("New label");
		GridBagConstraints gbc_lblMemberLoyaltyPts = new GridBagConstraints();
		gbc_lblMemberLoyaltyPts.anchor = GridBagConstraints.WEST;
		gbc_lblMemberLoyaltyPts.insets = new Insets(0, 0, 5, 0);
		gbc_lblMemberLoyaltyPts.gridx = 2;
		gbc_lblMemberLoyaltyPts.gridy = 3;
		rightEnterAmountPanel.add(lblMemberLoyaltyPts, gbc_lblMemberLoyaltyPts);

		lbl3 = new JLabel("Member Dollars to Redem:");
		GridBagConstraints gbc_lbl3 = new GridBagConstraints();
		gbc_lbl3.anchor = GridBagConstraints.EAST;
		gbc_lbl3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl3.gridx = 1;
		gbc_lbl3.gridy = 4;
		rightEnterAmountPanel.add(lbl3, gbc_lbl3);

		txtMemberDollarRedem = new JTextField();
		GridBagConstraints gbc_txtMemberDollarRedem = new GridBagConstraints();
		gbc_txtMemberDollarRedem.anchor = GridBagConstraints.WEST;
		gbc_txtMemberDollarRedem.insets = new Insets(0, 0, 5, 0);
		gbc_txtMemberDollarRedem.gridx = 2;
		gbc_txtMemberDollarRedem.gridy = 4;
		rightEnterAmountPanel.add(txtMemberDollarRedem, gbc_txtMemberDollarRedem);
		txtMemberDollarRedem.setColumns(10);

		lblAmountReceived = new JLabel("Amount Received:");
		GridBagConstraints gbc_lblAmountReceived = new GridBagConstraints();
		gbc_lblAmountReceived.anchor = GridBagConstraints.EAST;
		gbc_lblAmountReceived.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmountReceived.gridx = 1;
		gbc_lblAmountReceived.gridy = 6;
		rightEnterAmountPanel.add(lblAmountReceived, gbc_lblAmountReceived);

		txtAmountReceived = new JTextField();
		GridBagConstraints gbc_txtAmountReceived = new GridBagConstraints();
		gbc_txtAmountReceived.anchor = GridBagConstraints.WEST;
		gbc_txtAmountReceived.insets = new Insets(0, 0, 5, 0);
		gbc_txtAmountReceived.gridx = 2;
		gbc_txtAmountReceived.gridy = 6;
		rightEnterAmountPanel.add(txtAmountReceived, gbc_txtAmountReceived);
		txtAmountReceived.setColumns(10);

		JButton btnMakePayment = new JButton("Make Payment");
		GridBagConstraints gbc_btnMakePayment = new GridBagConstraints();
		gbc_btnMakePayment.insets = new Insets(0, 0, 5, 5);
		gbc_btnMakePayment.gridx = 1;
		gbc_btnMakePayment.gridy = 8;
		rightEnterAmountPanel.add(btnMakePayment, gbc_btnMakePayment);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 8;
		rightEnterAmountPanel.add(btnNewButton, gbc_btnNewButton);

		JLabel lbl6 = new JLabel("Change:");
		GridBagConstraints gbc_lbl6 = new GridBagConstraints();
		gbc_lbl6.anchor = GridBagConstraints.EAST;
		gbc_lbl6.insets = new Insets(0, 0, 0, 5);
		gbc_lbl6.gridx = 1;
		gbc_lbl6.gridy = 14;
		rightEnterAmountPanel.add(lbl6, gbc_lbl6);

		JLabel lblChange = new JLabel("New label");
		GridBagConstraints gbc_lblChange = new GridBagConstraints();
		gbc_lblChange.anchor = GridBagConstraints.WEST;
		gbc_lblChange.gridx = 2;
		gbc_lblChange.gridy = 14;
		rightEnterAmountPanel.add(lblChange, gbc_lblChange);

		rightFinishPaymentPanel = new JPanel();
		frame.getContentPane().add(rightFinishPaymentPanel);
		rightFinishPaymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnFinishAndPay = new JButton("Finish and Pay");
		btnFinishAndPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Finish buying items

				frame.remove(rightFinishPaymentPanel);
				frame.getContentPane().add(rightMemberPanel);
				frame.revalidate(); // For Java 1.7 or above.
				// frame.getContentPane().validate(); // For Java 1.6 or below.
				frame.repaint();

			}
		});
		btnFinishAndPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rightFinishPaymentPanel.add(btnFinishAndPay);

		rightMemberPanel = new JPanel();

		JButton btnMemberPay = new JButton("Member");
		btnMemberPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MemberLoginDialog memberLoginDlg = new MemberLoginDialog();

				memberLoginDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
				memberLoginDlg.setLocationRelativeTo(null);
				memberLoginDlg.setVisible(true);

				if (memberLoginDlg.isMember()) {
					// TODO

					memberMakePayment();
				}

			}
		});

		btnMemberPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rightMemberPanel.add(btnMemberPay);

		JButton btnNonMemberPay = new JButton("Non Member");
		btnNonMemberPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO
				nonMemberMakePayment();

			}
		});
		btnNonMemberPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rightMemberPanel.add(btnNonMemberPay);

		final JPanel reportingTransactionPanel = new ReportTransactionPanel(frame, reportingService);
		// reportingTransactionPanel.add(scrollPane);

		JMenuItem mntmTransactions = new JMenuItem("Transactions");
		mntmTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(leftPanel);
				frame.remove(rightFinishPaymentPanel);
				frame.remove(rightEnterAmountPanel);
				frame.remove(rightMemberPanel);

				frame.getContentPane().add(reportingTransactionPanel);

				frame.revalidate(); // For Java 1.7 or above.
				// frame.getContentPane().validate(); // For Java 1.6 or below.
				frame.repaint();
			}
		});
		mnReporting.add(mntmTransactions);

	}

	private void memberMakePayment() {

		lbl1.setVisible(true);
		lblMemberName.setVisible(true);
		lbl2.setVisible(true);
		lbl3.setVisible(true);
		lblMemberLoyaltyPts.setVisible(true);
		txtMemberDollarRedem.setVisible(true);

		frame.remove(rightMemberPanel);
		frame.getContentPane().add(rightEnterAmountPanel);
		frame.revalidate(); // For Java 1.7 or above.
		// frame.getContentPane().validate(); // For Java 1.6 or below.
		frame.repaint();

	}

	private void nonMemberMakePayment() {

		lbl1.setVisible(false);
		lblMemberName.setVisible(false);
		lbl2.setVisible(false);
		lbl3.setVisible(false);
		lblMemberLoyaltyPts.setVisible(false);
		txtMemberDollarRedem.setVisible(false);

		frame.remove(rightMemberPanel);
		frame.getContentPane().add(rightEnterAmountPanel);
		frame.revalidate(); // For Java 1.7 or above.
		// frame.getContentPane().validate(); // For Java 1.6 or below.
		frame.repaint();

	}

	private void readBarcode() {

		String barcode = txtBarcode.getText();

		for (Product product : this.products) {
			if (Integer.toString(product.getBarCodeNumber()).equals(barcode)) {

				this.shoppingcart.addRow(new Object[]{product.getName(), product.getPrice()});
				subTotal += product.getPrice();
				
				NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();
				
				lblsubTotal.setText(currencyIntance.format(subTotal));
				
				break;

			}
		}

	}

}
