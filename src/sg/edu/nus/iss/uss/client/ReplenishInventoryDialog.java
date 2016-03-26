package sg.edu.nus.iss.uss.client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IProductService;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ReplenishInventoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static final long serialVersionUID = 1L;
	private JTable table;
	private IProductService productService;

	/**
	 * Create the dialog.
	 */
	public ReplenishInventoryDialog(IProductService productService) {

		this.productService = productService;

		List<Product> products = this.productService.retrieveProductListByThreshold();

		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// Object[] columnNames = { "Product Name", "Current Quantity", "Re
		// Order Quantity", "Quantity to Order",
		// "Boolean" };
		// Object[][] data = { { "Buy", "IBM", new Integer(1000), new
		// Double(80.50), false },
		// { "Sell", "MicroSoft", new Integer(2000), new Double(6.25), false },
		// { "Sell", "Apple", new Integer(3000), new Double(7.35), false },
		// { "Buy", "Nortel", new Integer(4000), new Double(20.00), false } };
		//
		//
		//
		// DefaultTableModel model = new DefaultTableModel(data, columnNames);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Product Name");
		model.addColumn("Current Quantity");
		model.addColumn("Re Order Quantity");
		model.addColumn("Quantity to Order");
		model.addColumn("Boolean");
		
		for (int x=0; x< products.size();x++){
			Product product = products.get(x);
			
			model.insertRow(x, new Object[] { product.getName(), product.getQuantityAvailable(), 
					product.getReorderQuantity(), product.getOrderQuantity(), false});
			
			
			
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
					return Integer.class;
				case 3:
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
