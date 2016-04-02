package sg.edu.nus.iss.uss.client;

import sg.edu.nus.iss.uss.client.reporting.ReportCategoryDialog;
import sg.edu.nus.iss.uss.client.reporting.ReportMemberDialog;
import sg.edu.nus.iss.uss.client.reporting.ReportProductDialog;
import sg.edu.nus.iss.uss.client.reporting.ReportTransactionDialog;
import sg.edu.nus.iss.uss.dao.*;
import sg.edu.nus.iss.uss.dao.filedataaccess.*;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.ConsoleIPrinter;
import sg.edu.nus.iss.uss.model.IPrinter;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.*;
import sg.edu.nus.iss.uss.service.impl.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.List;

public class Application {

    private JFrame frame;
    private JTextField txtBarcode;

    private JPanel leftPanel, rightEnterAmountPanel;
    private JPanel rightFinishPaymentPanel;
    private JPanel rightMemberPanel;

    private IStoreKeeperDataAccess storeKeeperDAO;
    private IProductDataAccess productDAO;
    private ICategoryDataAccess categoryDAO;
    private ITransactionDataAccess transactionDAO;
    private IMemberDataAccess memberDAO;

    private IAuthorisedService authService;
    private IReportingService reportingService;
    private IProductService productService;
    private ITransactionService transactionService;
    private ICategoryService categoryService;
    private IMemberService memberService;
    private IDiscountService discountService;
    private ICheckOutService checkoutService;

    private Member member;

    private double subTotal = 0;

    private JTextField txtAmountReceived;
    private JTextField txtMemberDollarRedem;

    private JLabel lbl1, lbl2, lbl3, lblsubTotal, lblChange;
    private JLabel lblMemberName, lblMemberLoyaltyPts, lblAmountReceived;

    private JButton btnMakePayment;

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
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), ErrorConstants.SYSTEM_ERROR, "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initialize the contents of the frame.
     *
     * @throws UssException
     */
    private void initialize() throws UssException {

        // Initialize Services
        initializeServicesAndDaos();

        // Initialize Table Model use for shopping cart
        shoppingcart = new DefaultTableModel(0,0) {

            private static final long serialVersionUID = -4809733128016120842L;

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
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
        

        loginDlg.addWindowListener(new WindowListener() {
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

                NewMemberDialog newMemberDlg = new NewMemberDialog(memberService);
                newMemberDlg.setLocationRelativeTo(null);
                newMemberDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                newMemberDlg.setVisible(true);

            }
        });
        mnFile.add(mntmNewMenuItem);

        JMenu mnInventory = new JMenu("Inventory");
        menuBar.add(mnInventory);

        JMenuItem mntmAddStock = new JMenuItem("Add Stock");
        mntmAddStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReplenishInventoryDialog addStockDlg = new ReplenishInventoryDialog(productService);

                addStockDlg.setLocationRelativeTo(null);
                addStockDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                addStockDlg.setVisible(true);

            }
        });
        mnInventory.add(mntmAddStock);

        JMenuItem mntmNewProduct = new JMenuItem("New Product");
        mntmNewProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                NewProductDialog newProductDlg = new NewProductDialog(categoryService, productService);
                newProductDlg.setLocationRelativeTo(null);
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
                categoryDlg.setLocationRelativeTo(null);
                categoryDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                categoryDlg.setVisible(true);

            }
        });
        mnCategory.add(mntmNewCategory);

        JMenu mnPromotion = new JMenu("Promotion");
        menuBar.add(mnPromotion);

        JMenuItem mntmNewPromotion = new JMenuItem("New Promotion");
        mntmNewPromotion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewPromotionDialog promotionDlg = new NewPromotionDialog(discountService);
                promotionDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                promotionDlg.setVisible(true);
            }
        });
        mnPromotion.add(mntmNewPromotion);
        JMenuItem mntmUpdatePromotion = new JMenuItem("Update Promotion");
        mntmUpdatePromotion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                UpdateDiscountDialog updatePromotionDlg = new UpdateDiscountDialog(discountService);
                updatePromotionDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                updatePromotionDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                updatePromotionDlg.setVisible(true);
            }
        });
        mnPromotion.add(mntmUpdatePromotion);

        initializeReportingComponents(menuBar);

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

        final JButton btnAdd = new JButton("Add");
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

        btnMakePayment = new JButton("Make Payment");
        btnMakePayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (member != null) {
                    memberMakePayment();
                } else {
                    notMemberMakePayment();

                }
            }
        });

        GridBagConstraints gbc_btnMakePayment = new GridBagConstraints();
        gbc_btnMakePayment.insets = new Insets(0, 0, 5, 5);
        gbc_btnMakePayment.gridx = 1;
        gbc_btnMakePayment.gridy = 8;
        rightEnterAmountPanel.add(btnMakePayment, gbc_btnMakePayment);

        JButton btnNewButton = new JButton("Finish/Cancel Transaction");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                member = null;
                subTotal = 0;

                if (shoppingcart.getRowCount() > 0) {
                    for (int i = shoppingcart.getRowCount() - 1; i > -1; i--) {
                        shoppingcart.removeRow(i);
                    }
                }

                NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();
                lblsubTotal.setText(currencyIntance.format(subTotal));

                checkoutService = new CheckOutService(memberService, transactionService, productService);


                frame.remove(rightEnterAmountPanel);
                frame.remove(rightMemberPanel);
                frame.getContentPane().add(rightFinishPaymentPanel);
                frame.revalidate(); // For Java 1.7 or above.
                // frame.getContentPane().validate(); // For Java 1.6 or below.
                frame.repaint();

                txtAmountReceived.setText("");
                txtMemberDollarRedem.setText("");
                lblChange.setText("");

                btnAdd.setEnabled(true);
                btnMakePayment.setEnabled(true);
                txtAmountReceived.setEnabled(true);
                txtMemberDollarRedem.setEnabled(true);
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

        lblChange = new JLabel("");
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
                // No Items added to shopping cart
                if (shoppingcart.getRowCount() == 0) {

                    JOptionPane.showMessageDialog(new JFrame(), "No Items in shopping cart.", "Shopping Cart",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Finish buying items

                frame.remove(rightFinishPaymentPanel);
                frame.getContentPane().add(rightMemberPanel);
                frame.revalidate(); // For Java 1.7 or above.
                // frame.getContentPane().validate(); // For Java 1.6 or below.
                frame.repaint();

                btnAdd.setEnabled(false);

            }
        });
        btnFinishAndPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        rightFinishPaymentPanel.add(btnFinishAndPay);

        rightMemberPanel = new JPanel();

        JButton btnMemberPay = new JButton("Member");
        btnMemberPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MemberLoginDialog memberLoginDlg = new MemberLoginDialog(checkoutService);

                memberLoginDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                memberLoginDlg.setLocationRelativeTo(null);
                memberLoginDlg.setVisible(true);

                if (memberLoginDlg.isMember()) {

                    member = memberService.getMemberByMemberID(memberLoginDlg.getMemberID());
                    memberWantsToMakePayment();
                }

            }
        });

        btnMemberPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        rightMemberPanel.add(btnMemberPay);

        JButton btnNonMemberPay = new JButton("Non Member");
        btnNonMemberPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // TODO

                nonMemberWantsToMakePayment();

            }
        });
        btnNonMemberPay.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        rightMemberPanel.add(btnNonMemberPay);



    }

    private void initializeReportingComponents(JMenuBar menuBar) {
        JMenu mnReporting = new JMenu("Reporting");
        menuBar.add(mnReporting);

        JMenuItem mntmCategories = new JMenuItem("Categories");
        mntmCategories.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ReportCategoryDialog reportCategoryDialog = new ReportCategoryDialog(frame, reportingService);

                reportCategoryDialog.setModalityType(ModalityType.TOOLKIT_MODAL);
                reportCategoryDialog.setLocationRelativeTo(null);
                reportCategoryDialog.setVisible(true);

            }
        });
        mnReporting.add(mntmCategories);

        JMenuItem mntmProducts = new JMenuItem("Products");
        mntmProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportProductDialog reportProductDialog = new ReportProductDialog(frame, productService);

                reportProductDialog.setModalityType(ModalityType.TOOLKIT_MODAL);
                reportProductDialog.setLocationRelativeTo(null);
                reportProductDialog.setVisible(true);
            }
        });
        mnReporting.add(mntmProducts);

        JMenuItem mntmMembers = new JMenuItem("Members");
        mntmMembers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportMemberDialog reportMemberDialog = new ReportMemberDialog(frame, reportingService);

                reportMemberDialog.setModalityType(ModalityType.TOOLKIT_MODAL);
                reportMemberDialog.setLocationRelativeTo(null);
                reportMemberDialog.setVisible(true);
            }
        });
        mnReporting.add(mntmMembers);

        JMenuItem mntmTransactions = new JMenuItem("Transactions");
        mntmTransactions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportTransactionDialog reportTransactionDlg = new ReportTransactionDialog(frame, reportingService);

                reportTransactionDlg.setModalityType(ModalityType.TOOLKIT_MODAL);
                reportTransactionDlg.setLocationRelativeTo(null);
                reportTransactionDlg.setVisible(true);
            }
        });
        mnReporting.add(mntmTransactions);

    }

    private void initializeServicesAndDaos() throws UssException {
        this.storeKeeperDAO = new StoreKeeperFileDataAccess();
        this.authService = new AuthorisedService(storeKeeperDAO);

        this.transactionDAO = new TransactionFileDataAccess();
        this.transactionService = new TransactionService(transactionDAO);

        this.discountService = new DiscountService(new DiscountFileDataAccess());

        this.productDAO = new ProductFileDataAccess();
        this.categoryDAO = new CategoryFileDataAccess();
        this.productService = new ProductService(productDAO);

        this.categoryService = new CategoryService(categoryDAO);

        this.memberDAO = new MemberFileDataAccess();
        this.memberService = new MemberService(memberDAO);

        this.reportingService = new ReportingService(transactionService, productService, memberService, categoryService);

        this.checkoutService = new CheckOutService(this.memberService, this.transactionService, this.productService);
    }

    private void memberWantsToMakePayment() {

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

        // TODO add loyality points
        lblMemberName.setText(this.member.getName());
        lblMemberLoyaltyPts.setText("" + this.checkoutService.convertPointToDollarForDebit(this.member.getLoyaltyPoint()));

        double discountPercent = this.discountService.getMembersTodaysHighestDiscount(true,
                this.memberService.isFirstPurchase(this.member.getMemberID()));

        subTotal = this.checkoutService.calculatePayAmount(discountPercent);

        this.shoppingcart.addRow(new Object[] { "Discount", "" + discountPercent + "%" });

        NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();

        lblsubTotal.setText(currencyIntance.format(subTotal));

    }

    private void nonMemberWantsToMakePayment() {

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

        double discountPercent = this.discountService.getMembersTodaysHighestDiscount(false, false);

        subTotal = this.checkoutService.calculatePayAmount(discountPercent);

        this.shoppingcart.addRow(new Object[] { "Discount", "" + discountPercent + "%" });

        NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();

        lblsubTotal.setText(currencyIntance.format(subTotal));

    }

    private void memberMakePayment() {
        try {
            int dollarToRedeem = 0;

            dollarToRedeem = Integer.parseInt(txtMemberDollarRedem.getText());

            this.subTotal = this.checkoutService.calculateTotalPayable(this.subTotal, (int) dollarToRedeem);

            NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();

            lblsubTotal.setText(currencyIntance.format(subTotal));



            double amountReceived = Double.parseDouble(txtAmountReceived.getText());

            double change = this.checkoutService.memberMakePayment(amountReceived, dollarToRedeem);

            this.shoppingcart.addRow(new Object[] { "Deduct Loyalty Points in Dollars", "" + dollarToRedeem });

            lblChange.setText("$" + change);

            // check threshold
            checkIfRequiredReplenishStocks();

            // print receipt
            IPrinter printReceipt = new ConsoleIPrinter();

            int nRow = shoppingcart.getRowCount(), nCol = shoppingcart.getColumnCount();
            for (int i = 0; i < nRow; i++) {

                String row = "";

                for (int j = 0; j < nCol; j++) {
                    row += shoppingcart.getValueAt(i, j) + " ";
                }

                printReceipt.print(row);
            }

            printReceipt.print("Total $" + this.subTotal);
            printReceipt.print("Loyalty Points Deducted in Dollars $" + dollarToRedeem);

            int pointsAdded = checkoutService.convertDollarToPointForCredit(this.subTotal);

            printReceipt.print("Loyalty Points Awarded in this Transaction " + pointsAdded);
            printReceipt.print("Loyalty Points Remaining in Dollars $" + checkoutService.convertPointToDollarForDebit(this.member.getLoyaltyPoint()));
            printReceipt.print("Amount Received $" + amountReceived);
            printReceipt.print("Change $" + change);

            txtMemberDollarRedem.setEnabled(false);
            txtAmountReceived.setEnabled(false);
            btnMakePayment.setEnabled(false);

        } catch (UssException e) {
            lblChange.setText(e.getMessage());
        }catch (NumberFormatException err){
            JOptionPane.showMessageDialog(new JFrame(),
                    "Invalid Amount Entered", "Amount",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void checkIfRequiredReplenishStocks() {
        List<Product> products = this.checkoutService
                .alertIfInventoryLevelBelowThreshold(this.checkoutService.getCheckoutSummary().getCheckoutItems());
        for (Product product : products) {

            JOptionPane.showMessageDialog(new JFrame(),
                    "Product:  " + product.getName() + " has reach its reorder quantity.", "Inventory",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void notMemberMakePayment() {

        this.subTotal = this.checkoutService.calculateTotalPayable(this.subTotal, 0);

        NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();

        lblsubTotal.setText(currencyIntance.format(subTotal));

        try {

            double amountReceived = Double.parseDouble(txtAmountReceived.getText());

            double change = this.checkoutService.nonMemberMakePayment(amountReceived);

            lblChange.setText("$" + change);

            // check threshold
            checkIfRequiredReplenishStocks();

            // print receipt
            IPrinter printReceipt = new ConsoleIPrinter();

            int nRow = shoppingcart.getRowCount(), nCol = shoppingcart.getColumnCount();
            for (int i = 0; i < nRow; i++) {

                String row = "";

                for (int j = 0; j < nCol; j++) {
                    row += shoppingcart.getValueAt(i, j) + " ";
                }

                printReceipt.print(row);
            }

            printReceipt.print("Total $" + this.subTotal);
            printReceipt.print("Amount Received $" + amountReceived);
            printReceipt.print("Change $" + change);

            txtAmountReceived.setEnabled(false);
            btnMakePayment.setEnabled(false);


        } catch (UssException e) {
            lblChange.setText(e.getMessage());
        }catch (NumberFormatException err){
            JOptionPane.showMessageDialog(new JFrame(),
                    "Invalid Amount Entered", "Amount",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void readBarcode() {

        String barcode = txtBarcode.getText();

        try {
            Product product = this.productService.getProductByBarcode(barcode);

            if (product.getQuantityAvailable() <= 0) {
                JOptionPane.showMessageDialog(new JFrame(), "No more stock for " + barcode + " (or " +product.getName() + ")", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            else {
                this.shoppingcart.addRow(new Object[] { product.getName(), product.getPrice() });
                subTotal += product.getPrice();

                NumberFormat currencyIntance = NumberFormat.getCurrencyInstance();

                lblsubTotal.setText(currencyIntance.format(subTotal));

                this.checkoutService.addItemIntoCheckOutList(product);
            }



        } catch (UssException e) {

            JOptionPane.showMessageDialog(new JFrame(), "Cannot find product with barcode: " + barcode, "",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        finally{
            txtBarcode.setText("");
        }

    }

}
