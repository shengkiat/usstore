package sg.edu.nus.iss.uss.client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.service.ICategoryService;
import sg.edu.nus.iss.uss.service.IProductService;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
public class NewProductDialog extends JDialog {
	private JTextField txtProductName;
	private JTextField txtDescription;
	private JTextField txtQuantityAvailable;
	private JTextField txtPrice;
	private JTextField txtBarcode;
	private JTextField txtReorderQuantity;
	private JTextField txtOrderQuantity;
	private	JComboBox cbxCategory= new JComboBox();;
	private DefaultListModel<String> currentCategogies;
	private ICategoryService categoryService;
	
	private IProductService productService;
	
	/**
	 * Create the dialog.
	 */
	public NewProductDialog(ICategoryService categoryService, final IProductService productService) {
		
		this.currentCategogies = new DefaultListModel();

		this.categoryService = categoryService;
		List<Category> categories = this.categoryService.retrieveCategoryList();
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			this.cbxCategory.addItem(category);
		}
		
		this.productService = productService;
		
		
		// 350
		setBounds(100, 100, 350, 550);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 2;
		getContentPane().add(lblName, gbc_lblName);
		
		txtProductName = new JTextField();
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 5, 0);
		gbc_txtProductName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductName.gridx = 3;
		gbc_txtProductName.gridy = 2;
		getContentPane().add(txtProductName, gbc_txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Category:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		

		GridBagConstraints gbc_cbxCategory = new GridBagConstraints();
		gbc_cbxCategory.insets = new Insets(0, 0, 5, 0);
		gbc_cbxCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxCategory.gridx = 3;
		gbc_cbxCategory.gridy = 3;
		getContentPane().add(cbxCategory, gbc_cbxCategory);
		
		JLabel lblDescription = new JLabel("Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 2;
		gbc_lblDescription.gridy = 4;
		getContentPane().add(lblDescription, gbc_lblDescription);
		
		txtDescription = new JTextField();
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescription.gridx = 3;
		gbc_txtDescription.gridy = 4;
		getContentPane().add(txtDescription, gbc_txtDescription);
		txtDescription.setColumns(10);
		
		JLabel lblQuantityAvailable = new JLabel("Quantity Available:");
		GridBagConstraints gbc_lblQuantityAvailable = new GridBagConstraints();
		gbc_lblQuantityAvailable.anchor = GridBagConstraints.EAST;
		gbc_lblQuantityAvailable.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantityAvailable.gridx = 2;
		gbc_lblQuantityAvailable.gridy = 5;
		getContentPane().add(lblQuantityAvailable, gbc_lblQuantityAvailable);
		
		txtQuantityAvailable = new JTextField();
		GridBagConstraints gbc_txtQuantityAvailable = new GridBagConstraints();
		gbc_txtQuantityAvailable.insets = new Insets(0, 0, 5, 0);
		gbc_txtQuantityAvailable.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantityAvailable.gridx = 3;
		gbc_txtQuantityAvailable.gridy = 5;
		getContentPane().add(txtQuantityAvailable, gbc_txtQuantityAvailable);
		txtQuantityAvailable.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 2;
		gbc_lblPrice.gridy = 6;
		getContentPane().add(lblPrice, gbc_lblPrice);
		
		txtPrice = new JTextField();
		GridBagConstraints gbc_txtPrice = new GridBagConstraints();
		gbc_txtPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrice.gridx = 3;
		gbc_txtPrice.gridy = 6;
		getContentPane().add(txtPrice, gbc_txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblBarcodeNumber = new JLabel("Barcode Number:");
		GridBagConstraints gbc_lblBarcodeNumber = new GridBagConstraints();
		gbc_lblBarcodeNumber.anchor = GridBagConstraints.EAST;
		gbc_lblBarcodeNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblBarcodeNumber.gridx = 2;
		gbc_lblBarcodeNumber.gridy = 7;
		getContentPane().add(lblBarcodeNumber, gbc_lblBarcodeNumber);
		
		txtBarcode = new JTextField();
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 0);
		gbc_txtBarcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode.gridx = 3;
		gbc_txtBarcode.gridy = 7;
		getContentPane().add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblReorderQuantity = new JLabel("Reorder Quantity:");
		GridBagConstraints gbc_lblReorderQuantity = new GridBagConstraints();
		gbc_lblReorderQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblReorderQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblReorderQuantity.gridx = 2;
		gbc_lblReorderQuantity.gridy = 8;
		getContentPane().add(lblReorderQuantity, gbc_lblReorderQuantity);
		
		txtReorderQuantity = new JTextField();
		GridBagConstraints gbc_txtReorderQuantity = new GridBagConstraints();
		gbc_txtReorderQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_txtReorderQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtReorderQuantity.gridx = 3;
		gbc_txtReorderQuantity.gridy = 8;
		getContentPane().add(txtReorderQuantity, gbc_txtReorderQuantity);
		txtReorderQuantity.setColumns(10);
		
		JLabel lblOrderQuantity = new JLabel("Order Quantity:");
		GridBagConstraints gbc_lblOrderQuantity = new GridBagConstraints();
		gbc_lblOrderQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblOrderQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderQuantity.gridx = 2;
		gbc_lblOrderQuantity.gridy = 9;
		getContentPane().add(lblOrderQuantity, gbc_lblOrderQuantity);
		
		txtOrderQuantity = new JTextField();
		GridBagConstraints gbc_txtOrderQuantity = new GridBagConstraints();
		gbc_txtOrderQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_txtOrderQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderQuantity.gridx = 3;
		gbc_txtOrderQuantity.gridy = 9;
		getContentPane().add(txtOrderQuantity, gbc_txtOrderQuantity);
		txtOrderQuantity.setColumns(10);
		
		final JLabel lblInfo = new JLabel("");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.anchor = GridBagConstraints.WEST;
		gbc_lblInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblInfo.gridx = 3;
		gbc_lblInfo.gridy = 10;
		getContentPane().add(lblInfo, gbc_lblInfo);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 11;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productName = txtProductName.getText();
				String description =  txtDescription.getText();
				Integer quantityAvailable =  Integer.parseInt(txtQuantityAvailable.getText());
				double price= Double.parseDouble(txtPrice.getText());
				String barcode= txtBarcode.getText();
				Integer reorderQuantity= Integer.parseInt(txtReorderQuantity.getText());
				Integer orderQuantity= Integer.parseInt(txtOrderQuantity.getText());
				
				String categoryCode = ((Category)cbxCategory.getSelectedItem()).getCode();
				
				try {
					productService.createNewProductEntry(categoryCode, productName, description, quantityAvailable, price, barcode, reorderQuantity, orderQuantity);
				
				
					lblInfo.setText(productName + "is added successfully.");
					lblInfo.setForeground(Color.black);
				
					txtProductName.setText("");
					txtDescription.setText("");
					txtQuantityAvailable.setText("");
					txtPrice.setText("");
					txtBarcode.setText("");
					txtReorderQuantity.setText("");
					txtOrderQuantity.setText("");
				
				} catch (UssException e1) {
					lblInfo.setText("Fail to add product: " + productName);
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
