package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Product;

public class ProductFileDataAccess extends FileDataAccess implements IProductDataAccess {
	private List<Product> ProductList;
	List<String[]> PrdList;
	String[] strPrd = new String[8];
	
	public ProductFileDataAccess() throws UssException {
		super("Products.dat");
	}

	@Override
	public List<Product> getAll() {
		return ProductList;
	}

	@Override
	public void create(Product e) throws UssException {

		strPrd[0] = e.getProductID();
		strPrd[1] = e.getName();
		strPrd[2] = e.getBriefDescription();
		strPrd[3] = "" + e.getQuantityAvailable();
		strPrd[4] = "" + e.getPrice();
		strPrd[5] = "" + e.getBarCodeNumber();
		strPrd[6] = "" + e.getReorderQuantity();
		strPrd[7] = "" + e.getOrderQuantity();
		writeNewLine(strPrd);
	}

	@Override
	public void update(Product e) throws UssException {
		strPrd[0] = e.getProductID();
		strPrd[1] = e.getName();
		strPrd[2] = e.getBriefDescription();
		strPrd[3] = "" + e.getQuantityAvailable();
		strPrd[4] = "" + e.getPrice();
		strPrd[5] = "" + e.getBarCodeNumber();
		strPrd[6] = "" + e.getReorderQuantity();
		strPrd[7] = "" + e.getOrderQuantity();
		overwriteLine(strPrd);
	}
	
	@Override
	protected void initialLoad() {
		PrdList = readAll(); 
		Integer iQtyAvailable;
		Double dblPrice;
		Integer iBarCodeNo;
		Integer iQtyReOrder;
		Integer iOrderQty;
		
		for(String[] str: PrdList)
		{
			iQtyAvailable = Integer.parseInt(str[3]);
			dblPrice = Double.parseDouble(str[4]);
			iBarCodeNo = Integer.parseInt(str[5]);
			iQtyReOrder = Integer.parseInt(str[6]);
			iOrderQty = Integer.parseInt(str[7]);
			
			ProductList.add(new Product(str[0],str[1],str[2],iQtyAvailable,dblPrice,iBarCodeNo,iQtyReOrder,iOrderQty));
				
		}
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[0];
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return 8;
	}
	
}
