package sg.edu.nus.iss.uss.model;

import java.util.Comparator;
import java.util.HashMap;

public class Product implements Comparator<Product> {	
	
	private String productID;
	private String name;
	private String briefDescription;
	private int quantityAvailable;
	private double price;
	private int barCodeNumber;
	private int reorderQuantity;
	private int orderQuantity;
	private int productNo;
	private int purchaseQty;
	
	//HashMap<String,int> noofPrdt = new HaspMap<String,int>();
	
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
	public int getpurchaseQty() {
		return (purchaseQty);
	}
	public void setpurchaseQty(int qtyPurchased) {
		this.purchaseQty = qtyPurchased;
		this.quantityAvailable = quantityAvailable - qtyPurchased;
	}
	public void DeductQtyAvailable(int PurchaseQty) {	
		   this.quantityAvailable = this.quantityAvailable - PurchaseQty;
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
		String sQtyAvail = "" + this.quantityAvailable;
		String sPrice = "" + this.price;
		String sBarCodeNo = "" + this.barCodeNumber;
		String sReOrderqty = "" + this.reorderQuantity;
		String sOrderqty = "" + this.orderQuantity;
		
		return 	(productID + "," + name + "," + briefDescription  + "," + sQtyAvail + "," + sPrice + "," + sBarCodeNo + "," + sReOrderqty + "," + sOrderqty);  
	     
	}
	
	public boolean isBelowThreshold(){
		boolean blnThreshold = ((this.quantityAvailable >= this.reorderQuantity) && (this.quantityAvailable > 0)) ? true : false;
	    return blnThreshold ;	    
	}
	
	public void setProductNo(int productNo){
		this.productNo = productNo;
	}
	
	public int getProductNo(){
		// extract numeric portion out of the string and 
	    // and compare them convert them to int
		 
        Integer sPos = this.productID.indexOf('/');
        Integer ePos = this.productID.indexOf(',');
		
        return ((productNo == 0) ? Integer.parseInt(this.productID.substring(sPos+1, ePos)) : this.productNo);
	}
	
	public String getCategoryCode(){
	    return this.getProductID().substring(0, this.getProductID().indexOf("/"));
	}
	
	 public static class ProductByIDNo implements Comparator<Product> {
	  @Override
	  public int compare(Product Prdt1, Product Prdt2) {
	       return Prdt1.getProductNo() - Prdt2.getProductNo();
	    }
	  }

		@Override
		public int compare(Product o1, Product o2) {
			// TODO Auto-generated method stub
			return 0;
		}

}