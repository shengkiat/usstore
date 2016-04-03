package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.model.TestProductBuilder;
import sg.edu.nus.iss.uss.model.TestTransactionBuilder;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.service.IMemberService;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.IReportingService;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;
import static org.junit.Assert.*;

public class ReportingServiceTest {
	
	private IReportingService reportingService;

	@Test(expected=NullPointerException.class)
	public void testRetrieveReportTransactionsShouldThrowExceptionForNullStartDate() throws UssException {
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		reportingService.retrieveReportTransactions(null, endDate);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRetrieveReportTransactionsShouldThrowExceptionForNullEndDate() throws UssException {
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-01");
		reportingService.retrieveReportTransactions(startDate, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRetrieveReportTransactionsShouldThrowExceptionForInvalidDates() throws UssException {
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-03");
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		reportingService.retrieveReportTransactions(startDate, endDate);
	}
	
	@Test
	public void testRetrieveReportTransactionsShouldRetrieveSuccessfullyWithSortedProductId() throws UssException {
		
		final String[] testProductIds = new String[] {
				"STA/2", "CLO/1", "STA/1", "STA/1", "CLO/1"
		};
		
		reportingService = new ReportingService(new MockITransactionService() {
			@Override
			public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate) {
				List<Transaction> transactions = new ArrayList<>();
				
				for(String productId : testProductIds) {
					transactions.add(new TestTransactionBuilder().withProductID(productId).build());
				}
				
				return transactions;
			}
		}
		, new MockIProductService()
		, new MockIMemberService(), null);
		
		Date startDate = UssCommonUtil.convertStringToDate("2001-01-01");
		Date endDate = UssCommonUtil.convertStringToDate("2999-12-31");
		List<ReportTransaction> reportTransactions = reportingService.retrieveReportTransactions(startDate, endDate);
		
		assertEquals(testProductIds.length, reportTransactions.size());
		
		Arrays.sort(testProductIds);
		
		for(int i = 0; i<reportTransactions.size(); i++) {
			ReportTransaction reportTransaction = reportTransactions.get(i);
			assertNotNull(reportTransaction.getProductID());
			assertNotNull(reportTransaction.getProductName());
			assertNotNull(reportTransaction.getProductBriefDescription());
			
			assertEquals(testProductIds[i], reportTransaction.getProductID());
		}
	}
	
	@Before
	public void setUp() {
		reportingService = new ReportingService(new MockITransactionService(), new MockIProductService(), new MockIMemberService(), null);
	}

	@After
	public void tearDown() {
		reportingService = null;
	}
	
	private class MockITransactionService implements ITransactionService {

		@Override
		public List<Transaction> retrieveTransactionListByDate(Date startDate, Date endDate) {
			List<Transaction> transactions = new ArrayList<>();
			
			transactions.add(new TestTransactionBuilder().withProductID("STA/2").build());
			transactions.add(new TestTransactionBuilder().withProductID("CLO/1").build());
			transactions.add(new TestTransactionBuilder().withProductID("STA/1").build());
			transactions.add(new TestTransactionBuilder().withProductID("STA/1").build());
			transactions.add(new TestTransactionBuilder().withProductID("CLO/1").build());
			
			return transactions;
		}
		
		@Override
		public void createTransactions(List<Product> products, String memberID,
				Date transactionDate) throws UssException {
			throw new RuntimeException("not expected to call");
		}
	}
	
	private class MockIProductService implements IProductService {
		
		private final Map<String, Product> products = new HashMap<>();
		
		{
			products.put("CLO/1", new TestProductBuilder().build());
			products.put("STA/1", new TestProductBuilder().build());
			products.put("STA/2", new TestProductBuilder().build());
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
		public boolean checkIfProductIsBelowThreshold(Product product) {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public void deductInventoryFromCheckout(List<Product> productItems) {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public List<Product> retrieveProductListByThreshold() {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public void createNewProductEntry(String categoryCode, String productName, String briefDescription,
				int QuantityAvailable, double price, String barCodeNumber, int reorderQuantity, int orderQuantity)
				throws UssException {
			throw new RuntimeException("not expected to call");
			
		}

		@Override
		public Product getProductByBarcode(String barcode) throws UssException {
			// TODO Auto-generated method stub
			throw new RuntimeException("not expected to call");
		}

		@Override
		public void replenishInventory(List<Product> productItems) {
			throw new RuntimeException("not expected to call");
		}

		@Override
		public boolean isProductStillAvailableInInventory(Product product, List<Product> productItems) {
			throw new RuntimeException("not expected to call");
		}
	}
	
	private class MockIMemberService implements IMemberService {

		@Override
		public List<Member> retrieveMemberList() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Member getMemberByMemberID(String memberID) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void registerNewMember(String name, String idCardNumber) throws UssException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateMemberLoyaltyPoint(String memberID, int point) throws UssException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addMemberLoyaltyPoint(int point, String memberID) throws UssException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deductMemberLoyltyPoint(int point, String memberID) throws UssException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isValidMember(String memberID) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFirstPurchase(String memberID) {
			// TODO Auto-generated method stub
			return false;
		}

	}
	
}
