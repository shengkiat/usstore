package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.dao.IDiscountDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.DaySpecialDiscount;
import sg.edu.nus.iss.uss.model.Discount;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscount;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class DiscountFileDataAccess extends FileDataAccess implements IDiscountDataAccess {

private static final String FILE_NAME = "Discounts.dat";
	
	private static final int FIELD_DISCOUNT_CODE = 0;
	private static final int FIELD_DISCOUNT_DESCRIPTION = 1;
	private static final int FIELD_DISCOUNT_START_DATE = 2;
	private static final int FIELD_DISCOUNT_PERIOD = 3;
	private static final int FIELD_DISCOUNT_PERCENTAGE = 4;
	private static final int FIELD_DISCOUNT_APPLICABLE = 5;
	
	private static final int TOTAL_FIELDS = 6;
	
	private List<Discount> records;

	public DiscountFileDataAccess() throws UssException {
		super(FILE_NAME);
	}
	
	public DiscountFileDataAccess(String fileName, String directory) throws UssException {
		super(fileName, directory);
	}
	
	@Override
	protected void initialLoad() throws UssException {
		records = new ArrayList<>();		
		List<String[]> stringContent = readAll();
		if(readAll().size() < 5) {
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.INVALID_DISCOUNT_RECORDS);
		}
		for(String[] arr : stringContent) {
			records.add(strArrayToDiscount(arr));
		}
	}

	@Override
	public List<Discount> getAll() {
		return records;
	}

	@Override
	public void create(Discount discount) throws UssException {
		if(isDiscountExist(discount.getDiscountCode()))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.DISCOUNT_EXISTS);
		writeNewLine(discountToStrArray(discount));
		records.add(discount);
	}

	@Override
	public void update(Discount discount) throws UssException {
		if(discount == null || !isDiscountExist(discount.getDiscountCode()))
			throw new UssException(ErrorConstants.UssCode.DISCOUNT, ErrorConstants.DISCOUNT_NOT_EXIST);
		for(Discount temp : getAll()) {
			if(discount.getDiscountCode().equalsIgnoreCase(temp.getDiscountCode()))
				records.set(records.indexOf(temp), discount);
		}
		overwriteLine(discountToStrArray(discount));
	}
	
	@Override
	public Discount getDiscountByDiscountCode(String discountCode) {
		
		if(discountCode == null || discountCode.isEmpty()){
			return null;
		}
		
		List<Discount> discounts = getAll();
		Discount discount = null;
		for(Discount temp : discounts){
			if(discountCode.equalsIgnoreCase(temp.getDiscountCode())){
				discount = temp;
			}
		}
		
		return discount;
	}
	
	@Override
	public boolean isDiscountExist(String discountCode){
		
		for(Discount temp : getAll()){
			if(discountCode.equalsIgnoreCase(temp.getDiscountCode())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[FIELD_DISCOUNT_CODE];
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return TOTAL_FIELDS;
	}
	
	private Discount strArrayToDiscount(String [] arr) throws UssException{
		
		String discountCode = arr[FIELD_DISCOUNT_CODE];
		String discountDes = arr[FIELD_DISCOUNT_DESCRIPTION];
		Double discountPercentage = Double.parseDouble(arr[FIELD_DISCOUNT_PERCENTAGE]);
		String memberOrAll = arr[FIELD_DISCOUNT_APPLICABLE];
		Discount discount = null;
		if(memberOrAll.equals("M")) {
			discount = new MemberOnlyDiscount(discountCode, discountDes, discountPercentage);
		}
		else if(memberOrAll.equals("A")) {
			Date date = UssCommonUtil.convertStringToDate(arr[FIELD_DISCOUNT_START_DATE]);
			int discountPeriod = Integer.parseInt(arr[FIELD_DISCOUNT_PERIOD]);
			discount = new DaySpecialDiscount(discountCode, discountDes, discountPercentage, date, discountPeriod);
		}
		
		return discount;
	}
	
	private String[] discountToStrArray(Discount discount){
		String[] arr = new String[TOTAL_FIELDS];
		if(discount instanceof MemberOnlyDiscount) {
			MemberOnlyDiscount moDiscount = (MemberOnlyDiscount)discount;
			arr[FIELD_DISCOUNT_CODE] = moDiscount.getDiscountCode();
			arr[FIELD_DISCOUNT_DESCRIPTION] = moDiscount.getDescription();
			arr[FIELD_DISCOUNT_START_DATE] = "ALWAYS";
			arr[FIELD_DISCOUNT_PERIOD] = "ALWAYS";
			arr[FIELD_DISCOUNT_PERCENTAGE] = "" + moDiscount.getDiscountPercentage();
			arr[FIELD_DISCOUNT_APPLICABLE] = "M";
		}
		if(discount instanceof DaySpecialDiscount) {
			DaySpecialDiscount dsDiscount = (DaySpecialDiscount)discount;
			arr[FIELD_DISCOUNT_CODE] = dsDiscount.getDiscountCode();
			arr[FIELD_DISCOUNT_DESCRIPTION] = dsDiscount.getDescription();
			arr[FIELD_DISCOUNT_START_DATE] = UssCommonUtil.convertDateToString(dsDiscount.getStartDate());
			arr[FIELD_DISCOUNT_PERIOD] = "" + dsDiscount.getDiscountDays();
			arr[FIELD_DISCOUNT_PERCENTAGE] = "" + dsDiscount.getDiscountPercentage();
			arr[FIELD_DISCOUNT_APPLICABLE] = "A";
		}
		return arr;
	}
}
