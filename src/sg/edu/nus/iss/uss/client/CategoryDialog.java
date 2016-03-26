package sg.edu.nus.iss.uss.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.service.ICategoryService;

public class CategoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoryCode;
	private JTextField txtCategoryName;

	private DefaultListModel<String> currentCategogies;
	private ICategoryService categoryService;

	/**
	 * Create the dialog.
	 */
	public CategoryDialog(final ICategoryService categoryService) {

		this.currentCategogies = new DefaultListModel();

		this.categoryService = categoryService;
		List<Category> categories = this.categoryService.retrieveCategoryList();
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			this.currentCategogies.add(i, category.getName());
		}

		// this.currentCategogies

		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel categoryPanel = new JPanel();
		getContentPane().add(categoryPanel);

		String[] selections = { "CLothing", "Mugs", "Stationary", "dark blue" };

		JList categoryList = new JList(currentCategogies);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane scrollPane = new JScrollPane(categoryList);
		categoryPanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(300, 200));

		categoryPanel.setLayout(new BoxLayout(categoryPanel,
				BoxLayout.PAGE_AXIS));

		JPanel addCategoryPanel = new JPanel();
		getContentPane().add(addCategoryPanel);
		GridBagLayout gbl_addCategoryPanel = new GridBagLayout();
		gbl_addCategoryPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_addCategoryPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_addCategoryPanel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_addCategoryPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		addCategoryPanel.setLayout(gbl_addCategoryPanel);

		JLabel lblLetterCategory = new JLabel("3 Letter Category Code:");
		GridBagConstraints gbc_lblLetterCategory = new GridBagConstraints();
		gbc_lblLetterCategory.anchor = GridBagConstraints.EAST;
		gbc_lblLetterCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblLetterCategory.gridx = 1;
		gbc_lblLetterCategory.gridy = 1;
		addCategoryPanel.add(lblLetterCategory, gbc_lblLetterCategory);

		txtCategoryCode = new JTextField();
		GridBagConstraints gbc_txtCategoryCode = new GridBagConstraints();
		gbc_txtCategoryCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtCategoryCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoryCode.gridx = 2;
		gbc_txtCategoryCode.gridy = 1;
		addCategoryPanel.add(txtCategoryCode, gbc_txtCategoryCode);
		txtCategoryCode.setColumns(10);

		JLabel lblCategoryDescription = new JLabel("Category Description:");
		GridBagConstraints gbc_lblCategoryDescription = new GridBagConstraints();
		gbc_lblCategoryDescription.anchor = GridBagConstraints.EAST;
		gbc_lblCategoryDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoryDescription.gridx = 1;
		gbc_lblCategoryDescription.gridy = 3;
		addCategoryPanel
				.add(lblCategoryDescription, gbc_lblCategoryDescription);

		txtCategoryName = new JTextField();
		GridBagConstraints gbc_txtCategoryName = new GridBagConstraints();
		gbc_txtCategoryName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCategoryName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoryName.gridx = 2;
		gbc_txtCategoryName.gridy = 3;
		addCategoryPanel.add(txtCategoryName, gbc_txtCategoryName);
		txtCategoryName.setColumns(10);

		final JLabel lblInfo = new JLabel("");
		lblInfo.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo.gridx = 1;
		gbc_lblInfo.gridy = 5;
		addCategoryPanel.add(lblInfo, gbc_lblInfo);

		JButton btnAddNewCategory = new JButton("Add New Category");
		btnAddNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryCode = txtCategoryCode.getText();
				String categoryName = txtCategoryName.getText();

				if (categoryCode.length() != 3) {
					lblInfo.setText("Fail to add" + categoryCode);
					lblInfo.setForeground(Color.RED);

					return;
				}

				try {
					categoryService.createNewCategory(categoryCode,
							categoryName);
					lblInfo.setText(categoryCode + " is added successfully.");
					lblInfo.setForeground(Color.black);
					currentCategogies.add(currentCategogies.size(),
							categoryName);

				} catch (UssException e1) {
					lblInfo.setText("Fail to add" + categoryCode);
					lblInfo.setForeground(Color.RED);
				}

			}
		});
		GridBagConstraints gbc_btnAddNewCategory = new GridBagConstraints();
		gbc_btnAddNewCategory.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddNewCategory.gridx = 1;
		gbc_btnAddNewCategory.gridy = 9;
		addCategoryPanel.add(btnAddNewCategory, gbc_btnAddNewCategory);

		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 9;
		addCategoryPanel.add(btnCancel, gbc_btnCancel);

	}

}
