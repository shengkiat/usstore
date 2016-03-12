package sg.edu.nus.iss.uss.model;

public class Product {
	private String productID;
	private String name;
	private String briefDescription;
	private int quantityAvailable;
	private double price;
	private int barCodeNumber;
	private int reorderQuantity;
	private int orderQuantity;
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBriefDescription() {
		return briefDescription;
	}
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	public int getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBarCodeNumber() {
		return barCodeNumber;
	}
	public void setBarCodeNumber(int barCodeNumber) {
		this.barCodeNumber = barCodeNumber;
	}
	public int getReorderQuantity() {
		return reorderQuantity;
	}
	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	public Product(String productID, String name, String briefDescription, int quantityAvailable, double price,
			int barCodeNumber, int reorderQuantity, int orderQuantity) {
		super();
		this.productID = productID;
		this.name = name;
		this.briefDescription = briefDescription;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.barCodeNumber = barCodeNumber;
		this.reorderQuantity = reorderQuantity;
		this.orderQuantity = orderQuantity;
	}
	@Override
	public String toString() {//STA/1,NUS Pen,A really cute blue pen,768,5.75,123459876,50,250
		//TODO toString
		
		return "";
	}
	
	public boolean isBelowThreshold(){
		
		return false; //TODO
	}
}
