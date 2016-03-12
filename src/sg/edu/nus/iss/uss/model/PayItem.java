package sg.edu.nus.iss.uss.model;

public class PayItem extends Product {

	private double chargePrice;

	public PayItem(String productID, String name, String briefDescription, int quantityAvailable, double price,
			int barCodeNumber, int reorderQuantity, int orderQuantity) {
		super(productID, name, briefDescription, quantityAvailable, price, barCodeNumber, reorderQuantity,
				orderQuantity);

	}

	public PayItem(String productID, String name, String briefDescription, int quantityAvailable, int price,
			int barCodeNumber, int reorderQuantity, int orderQuantity, double chargePrice) {
		super(productID, name, briefDescription, quantityAvailable, price, barCodeNumber, reorderQuantity,
				orderQuantity);

		this.chargePrice = chargePrice;
	}

	public PayItem(Product product, double chargePrice) {
		super(product.getProductID(), product.getName(), product.getBriefDescription(), product.getQuantityAvailable(), product.getPrice(),
				product.getBarCodeNumber(), product.getReorderQuantity(), product.getOrderQuantity());

		this.chargePrice = chargePrice;
	}

	public double getChargePrice() {
		return chargePrice;
	}

	public void setChargePrice(double chargePrice) {
		this.chargePrice = chargePrice;
	}

}
