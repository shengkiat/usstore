package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.PublicBuyer;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.IReportingService;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.TestUtil;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class ReportingServiceTest {
	
	private IReportingService reportingService;

	@Test(expected=NullPointerException.class)
	public void testRetrieveReportTransactionsShouldThrowExceptionForNullStartDate() {
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		reportingService.retrieveReportTransactions(null, endDate);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRetrieveReportTransactionsShouldThrowExceptionForNullEndDate() {
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-01");
		reportingService.retrieveReportTransactions(startDate, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRetrieveReportTransactionsShouldThrowExceptionForInvalidDates() {
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-03");
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		reportingService.retrieveReportTransactions(startDate, endDate);
	}
	
	@Before
	public void setUp() {
		reportingService = new ReportingService(new MockITransactionService(), new MockIProductService());
	}

	@After
	public void tearDown() {
		reportingService = null;
	}
	
	private class MockITransactionService implements ITransactionService {

		@Override
		public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate) {
			List<Transaction> transactions = new ArrayList<>();
			
			transactions.add(new Transaction("CLO/1", PublicBuyer.PUBLIC_NAME, 1, UssCommonUtil.convertStringToDate("2016-01-01")));
			transactions.add(new Transaction("CLO/1", PublicBuyer.PUBLIC_NAME, 1, UssCommonUtil.convertStringToDate("2016-02-01")));
			transactions.add(new Transaction("STA/1", "12345678", 1, UssCommonUtil.convertStringToDate("2016-03-01")));
			transactions.add(new Transaction("STA/1", "22225555", 1, UssCommonUtil.convertStringToDate("2016-04-01")));
			transactions.add(new Transaction("STA/2", "33334456", 1, UssCommonUtil.convertStringToDate("2016-05-01")));
			
			return transactions;
		}

		@Override
		public void createTransactions(List<Transaction> transactions) throws UssException {
			throw new RuntimeException("not expected to call");
		}
	}
	
	private class MockIProductService implements IProductService {
		
		private final Map<String, Product> products = new HashMap<>();
		
		{
			products.put("CLO/1", null);
			products.put("STA/1", null);
			products.put("STA/2", null);
		}

		@Override
		public Product getProductByProductID(String productID) {
			return products.get(productID);
		}

		@Override
		public List<Product> retrieveProductList() {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public List<Product> retrieveProductListByThreshold(int threshold) {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public Product createNewProductEntry(String categoryCode, String productName, String briefDescription,
				int price, int barCodeNumber, int reorderQuantity, int orderQuantity) {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public boolean checkIfProductIsBelowThreshold(Product product) {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public void deductInventoryFromCheckout(List<Product> productItems) {
			throw new RuntimeException("not expected to call");
		}
	}
}
