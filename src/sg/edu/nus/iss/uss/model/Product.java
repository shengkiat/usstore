package sg.edu.nus.iss.uss.model;

import java.util.Comparator;

public class Product implements Comparator<Product> {	
	
	private String productID;
	private String name;
	private String briefDescription;
	private int quantityAvailable;
	private double price;
	private String barCodeNumber;
	private int reorderQuantity;
	private int orderQuantity;
	
	////////////////////////////////////////////////////
	private int productNo;
	private String CategoryCode;
	//private Boolean isBelowThreshold;
	////////////////////////////////////////////////////
	
	private volatile int hashcode;

	
	public String getProductID() {
		return productID;
	}
	
	public void setProductID(String catCode,int prdtNo) {
		this.productID = catCode + "/" + String.valueOf(prdtNo) ;
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
		return (quantityAvailable);
	}
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
		///////////////////////////////////////////////////////////////////////////////////////
		//this.isBelowThreshold = (this.quantityAvailable < this.reorderQuantity) ? true:false ;
		///////////////////////////////////////////////////////////////////////////////////////
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBarCodeNumber() {
		return barCodeNumber;
	}
	public void setBarCodeNumber(String barCodeNumber) {
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
			String barCodeNumber, int reorderQuantity, int orderQuantity) {
		super();
		this.productID = productID;
		this.name = name;
		this.briefDescription = briefDescription;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.barCodeNumber = barCodeNumber;
		this.reorderQuantity = reorderQuantity;
		this.orderQuantity = orderQuantity;
        
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        Integer sPos = this.productID.indexOf('/');
        Integer ePos = this.productID.length();
        this.productNo = Integer.parseInt(this.productID.substring(sPos+1, ePos));
		this.CategoryCode = this.productID.substring(0, this.productID.indexOf("/")).toUpperCase();
		////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public int getProductNo(){
		return this.productNo;
	}
	
	public String getCategoryCode(){
		return this.CategoryCode;
	}
	
	public boolean isBelowThreshold(){
	    return this.quantityAvailable < this.reorderQuantity;	    
	    //return this.isBelowThreshold;
	}
	
	@Override
	public int compare(Product p1, Product p2) {	    
		return Integer.valueOf(p1.getProductNo()).compareTo(Integer.valueOf(p1.getProductNo()));      
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Product)) return false;
		Product p = (Product) o;
		
	    return p.productID == this.productID
	    && p.name == this.name
	    && p.briefDescription == this.briefDescription
	    && p.quantityAvailable == this.quantityAvailable
	    && p.price == this.price
	    && p.barCodeNumber == this.barCodeNumber
	    && p.reorderQuantity == this.reorderQuantity
	    && p.orderQuantity == this.orderQuantity
		&& p.productNo == this.productNo;
		
	}
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
		sb.append(this.productID);
		sb.append(this.name);
		sb.append(this.briefDescription);
		sb.append("" + this.quantityAvailable);
		sb.append("" + this.price);
		sb.append(this.barCodeNumber);
		sb.append("" + this.reorderQuantity);
		sb.append("" + this.orderQuantity);
	
		return sb.toString();
	}
	
	
	@Override
	public int hashCode() {
		int result = hashcode;
		String str;
		if (result ==0 ) {
		    result = 17;	
			result = 31 * result + this.productID.hashCode();
			result = 31 * result + this.name.hashCode();
			result = 31 * result + this.briefDescription.hashCode();
			str = "" + this.quantityAvailable;
			result = 31 * result + str.hashCode();
			str = "" + this.price;
			result = 31 * result + str.hashCode();
			result = 31 * result + this.barCodeNumber.hashCode();
			str = "" + this.reorderQuantity;
			result = 31 * result + str.hashCode();
			str = "" + this.orderQuantity;
			result = 31 * result + str.hashCode();
			
			hashcode = result;
		}
		return result;
	}
}