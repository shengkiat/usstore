package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.exception.ErrorConstants.UssCode;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Product;

public class ProductFileDataAccess extends FileDataAccess implements IProductDataAccess {
	private List<Product> productList;
	
	private static final String FILE_NAME = "Products.dat";
	private static final int FIELD_PRODUCT_ID = 0;
	private static final int FIELD_NAME = 1;
	private static final int FIELD_DESCRIPTION = 2;
	private static final int FIELD_QUANTITY_AVAILABLE = 3;
	private static final int FIELD_PRICE = 4;
	private static final int FIELD_BARCODENUMBER = 5;
	private static final int FIELD_REORDER_QUANTITY = 6;
	private static final int FIELD_ORDER_QUANTITY = 7;
	
	private static final int TOTAL_FIELDS = 8;
	
	public ProductFileDataAccess() throws UssException {
		super(FILE_NAME);
	}

	public ProductFileDataAccess(String fileName, String directory) throws UssException {
		super(fileName, directory);
	}
	
	@Override
	public List<Product> getAll() {
		return productList;
	}

	@Override
	public void create(Product e) throws UssException  {
		String[] strPrd = new String[TOTAL_FIELDS];
		
		strPrd[FIELD_PRODUCT_ID] = e.getProductID();
		strPrd[FIELD_NAME] = e.getName();
		strPrd[FIELD_DESCRIPTION] = e.getBriefDescription();
		strPrd[FIELD_QUANTITY_AVAILABLE] = "" + e.getQuantityAvailable();
		strPrd[FIELD_PRICE] = "" + e.getPrice();
		strPrd[FIELD_BARCODENUMBER] = "" + e.getBarCodeNumber();
		strPrd[FIELD_REORDER_QUANTITY] = "" + e.getReorderQuantity();
		strPrd[FIELD_ORDER_QUANTITY] = "" + e.getOrderQuantity();
		
		writeNewLine(strPrd);
		
		productList.add(e);
	}

	@Override
	public void update(Product e) throws UssException  {
		String[] strPrd = new String[TOTAL_FIELDS];
		strPrd[FIELD_PRODUCT_ID] = e.getProductID();
		strPrd[FIELD_NAME] = e.getName();
		strPrd[FIELD_DESCRIPTION] = e.getBriefDescription();
		strPrd[FIELD_QUANTITY_AVAILABLE] = "" + e.getQuantityAvailable();
		strPrd[FIELD_PRICE] = "" + e.getPrice();
		strPrd[FIELD_BARCODENUMBER] = "" + e.getBarCodeNumber();
		strPrd[FIELD_REORDER_QUANTITY] = "" + e.getReorderQuantity();
		strPrd[FIELD_ORDER_QUANTITY] = "" + e.getOrderQuantity();
		overwriteLine(strPrd);
		
		for(int i = 0; i<productList.size(); i++)
		{
			Product p = productList.get(i);
			if (p.getProductID().equalsIgnoreCase(strPrd[FIELD_PRODUCT_ID])) {
				productList.set(i, e);
			}
		}


	}
	
	@Override
	protected void initialLoad() throws UssException {
		
		productList = new ArrayList<>();
		
		List<String[]> prdList = readAll(); 
        
		if (prdList.isEmpty()) {
			throw new UssException(UssCode.PRODUCT,ErrorConstants.PRODUCTFILE_EMPTY);
			
		} else {
			Integer iQtyAvailable;
			Double dblPrice;
			String sBarCodeNo;
			Integer iQtyReOrder;
			Integer iOrderQty;
			
			Product prdt;
			
			for(String[] str: prdList)
			{
				iQtyAvailable = Integer.parseInt(str[FIELD_QUANTITY_AVAILABLE]);
				dblPrice = Double.parseDouble(str[FIELD_PRICE]);
				sBarCodeNo = str[FIELD_BARCODENUMBER];
				iQtyReOrder = Integer.parseInt(str[FIELD_REORDER_QUANTITY]);
				iOrderQty = Integer.parseInt(str[FIELD_ORDER_QUANTITY]);
				
				prdt = new Product(str[FIELD_PRODUCT_ID],str[FIELD_NAME],str[FIELD_DESCRIPTION],iQtyAvailable,dblPrice,sBarCodeNo,iQtyReOrder,iOrderQty);
				prdt.setProductNo(prdt.getProductNo());
				productList.add(prdt);
					
			}
		
		}
		
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[FIELD_PRODUCT_ID];
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return TOTAL_FIELDS;
	}

}
