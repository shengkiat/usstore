package sg.edu.nus.iss.uss.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.uss.service.ICategoryService;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class CategoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	private DefaultTableModel shoppingcart;
	private	ICategoryService categoryService;
	/**
	 * Create the dialog.
	 */
	public CategoryDialog(ICategoryService categoryService) {
this.categoryService = categoryService;
		
		
		
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel categoryPanel = new JPanel();
		getContentPane().add(categoryPanel);
		
		String[] selections = { "CLothing", "Mugs", "Stationary", "dark blue" };
		

		JList categoryList = new JList(selections);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane scrollPane = new JScrollPane(categoryList);
		categoryPanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.PAGE_AXIS));
		
		JPanel addCategoryPanel = new JPanel();
		getContentPane().add(addCategoryPanel);
		GridBagLayout gbl_addCategoryPanel = new GridBagLayout();
		gbl_addCategoryPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_addCategoryPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_addCategoryPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_addCategoryPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addCategoryPanel.setLayout(gbl_addCategoryPanel);
		
		JLabel lblLetterCategory = new JLabel("3 Letter Category Code:");
		GridBagConstraints gbc_lblLetterCategory = new GridBagConstraints();
		gbc_lblLetterCategory.anchor = GridBagConstraints.EAST;
		gbc_lblLetterCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblLetterCategory.gridx = 1;
		gbc_lblLetterCategory.gridy = 1;
		addCategoryPanel.add(lblLetterCategory, gbc_lblLetterCategory);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		addCategoryPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblCategoryDescription = new JLabel("Category Description:");
		GridBagConstraints gbc_lblCategoryDescription = new GridBagConstraints();
		gbc_lblCategoryDescription.anchor = GridBagConstraints.EAST;
		gbc_lblCategoryDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoryDescription.gridx = 1;
		gbc_lblCategoryDescription.gridy = 3;
		addCategoryPanel.add(lblCategoryDescription, gbc_lblCategoryDescription);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 3;
		addCategoryPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblError = new JLabel("New label");
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 5;
		addCategoryPanel.add(lblError, gbc_lblError);
		
		JButton btnAddNewCategory = new JButton("Add New Category");
		GridBagConstraints gbc_btnAddNewCategory = new GridBagConstraints();
		gbc_btnAddNewCategory.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddNewCategory.gridx = 1;
		gbc_btnAddNewCategory.gridy = 9;
		addCategoryPanel.add(btnAddNewCategory, gbc_btnAddNewCategory);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 9;
		addCategoryPanel.add(btnCancel, gbc_btnCancel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
