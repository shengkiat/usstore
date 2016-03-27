package sg.edu.nus.iss.uss.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

public class ReplenishInventoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static final long serialVersionUID = 1L;
	private JTable table;
	private IProductService productService;

	private List<Product> products;

	/**
	 * Create the dialog.
	 */
	public ReplenishInventoryDialog(final IProductService productService) {

		this.productService = productService;

		this.products = this.productService.retrieveProductListByThreshold();

		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		final DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Product ID");
		model.addColumn("Product Name");
		model.addColumn("Current Quantity");
		model.addColumn("Re Order Quantity");
		model.addColumn("Quantity to Order");
		model.addColumn("Boolean");

		for (int x = 0; x < products.size(); x++) {
			Product product = products.get(x);

			model.insertRow(x, new Object[] { product.getProductID(), product.getName(), product.getQuantityAvailable(),
					product.getReorderQuantity(), product.getOrderQuantity(), false });

		}

		table = new JTable(model) {

			/*
			 * @Override public Class getColumnClass(int column) { return
			 * getValueAt(0, column).getClass(); }
			 */
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Integer.class;
				case 4:
					return Double.class;
				default:
					return Boolean.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGenerateOrder = new JButton("Generate Order");
				btnGenerateOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						List<Product> productsToReplenish = new ArrayList<Product>();

						int nRow = model.getRowCount(), nCol = model.getColumnCount();
						//Object[][] tableData = new Object[nRow][nCol];
						for (int i = 0; i < nRow; i++) {

							boolean isChecked = (boolean) model.getValueAt(i, 5);
							String _prodID = (String) model.getValueAt(i, 0);

							if (isChecked) {
								for (Product product : products) {
									if (product.getProductID().equals(_prodID)) {
										productsToReplenish.add(product);
									}
								}
							}

						}

						productService.replenishInventory(productsToReplenish);

						// Refresh Table

						products = productService.retrieveProductListByThreshold();

						if (model.getRowCount() > 0) {
							for (int i = model.getRowCount() - 1; i > -1; i--) {
								model.removeRow(i);
							}
						}

						for (int x = 0; x < products.size(); x++) {
							Product product = products.get(x);

							model.insertRow(x,
									new Object[] { product.getProductID(), product.getName(),
											product.getQuantityAvailable(), product.getReorderQuantity(),
											product.getOrderQuantity(), false });

						}

					}
				});
				btnGenerateOrder.setActionCommand("OK");
				buttonPane.add(btnGenerateOrder);
				getRootPane().setDefaultButton(btnGenerateOrder);
			}
			{
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnClose.setActionCommand("Cancel");
				buttonPane.add(btnClose);
			}
		}

	}

}
