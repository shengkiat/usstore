package sg.edu.nus.iss.uss.model;

public class TestProductBuilder {
	
	private String productID = "CLO/1";
	private String name = "Centenary Jumper";
	private String briefDescription = "A really nice momento";
	private int quantityAvailable = 315;
	private double price = 21.45;
	private String barCodeNumber = "1234";
	private int reorderQuantity = 10;
	private int orderQuantity = 100;
	
	public TestProductBuilder withProductID(String productID) {
		this.productID = productID;
		return this;
	}
	
	public TestProductBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public TestProductBuilder withBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
		return this;
	}
	
	public TestProductBuilder withQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
		return this;
	}
	
	public TestProductBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public TestProductBuilder withBarCodeNumber(String barCodeNumber) {
		this.barCodeNumber = barCodeNumber;
		return this;
	}
	
	public TestProductBuilder withReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
		return this;
	}
	
	public TestProductBuilder withOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
		return this;
	}
	
	public Product build() {
		return new Product(productID, name, briefDescription, quantityAvailable, price,
				barCodeNumber, reorderQuantity, orderQuantity);
	}

}
