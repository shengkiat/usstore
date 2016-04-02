package sg.edu.nus.iss.uss.model;

import java.util.Comparator;


//Comparator<Product>,

public class Product implements Comparator<Product> {	
	
	private String productID;
	private String name;
	private String briefDescription;
	private int quantityAvailable;
	private double price;
	private String barCodeNumber;
	private int reorderQuantity;
	private int orderQuantity;
	//Added
	private int productNo;
	//private int purchaseQty;
	
	private volatile int hashcode;
	
	public Product() {}
	
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
	}
	/*
	public int getpurchaseQty() {
		return (purchaseQty);
	}
	public void setPurchaseQty(int qtyPurchased) throws UssException {
		this.purchaseQty = qtyPurchased;
		DeductQtyAvailable();
	}
	
	public void DeductQtyAvailable() {	
		   this.quantityAvailable = this.quantityAvailable - this.purchaseQty;
	}*/
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
	}
	/*@Override
	public String toString() {//STA/1,NUS Pen,A really cute blue pen,768,5.75,123459876,50,250
		//TODO toString
		String sQtyAvail = "" + this.quantityAvailable;
		String sPrice = "" + this.price;
		String sBarCodeNo = "" + this.barCodeNumber;
		String sReOrderqty = "" + this.reorderQuantity;
		String sOrderqty = "" + this.orderQuantity;
		
		return 	(productID + "," + name + "," + briefDescription  + "," + sQtyAvail + "," + sPrice + "," + sBarCodeNo + "," + sReOrderqty + "," + sOrderqty);  
	     
	}*/
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
		sb.append(this.productID);
		sb.append(this.name);
		sb.append(this.briefDescription);
		sb.append("" + this.quantityAvailable);
		sb.append("" + this.price);
		sb.append("" + this.barCodeNumber);
		sb.append("" + this.reorderQuantity);
		sb.append("" + this.orderQuantity);
		
		/*System.out.println("");
		System.out.println(sb.toString());*/
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
	
	
	public boolean isBelowThreshold(){
	    return this.quantityAvailable <= this.reorderQuantity;	    
	}
	
	public int getProductNo(){
		if (this.productNo == 0)     { 
	        Integer sPos = this.productID.indexOf('/');
	        Integer ePos = this.productID.length();
	        this.productNo = Integer.parseInt(this.productID.substring(sPos+1, ePos));
		}
        return this.productNo;
	}
	
	public String getCategoryCode(){
	    return this.getProductID().substring(0, this.getProductID().indexOf("/"));
	}
	

	@Override
	public int compare(Product p1, Product p2) {
	    
		return Integer.valueOf(p1.getProductNo()).compareTo(Integer.valueOf(p1.getProductNo()));
		/*int i = p1.getCategoryCode().compareTo(p2.getCategoryCode());
		if (i==0) i = p1.getProductNo() - p2.getProductNo();
		return i;*/
	     
	     
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
	
	
	
	
}