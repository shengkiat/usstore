package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.dao.ITransactionDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Product;
//import sg.edu.nus.iss.uss.model.PublicBuyer;
import sg.edu.nus.iss.uss.model.TestProductBuilder;
import sg.edu.nus.iss.uss.model.TestTransactionBuilder;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;

public class TransactionServiceTest {
	
	@Test(expected=NullPointerException.class)
	public void testRetrieveTransactionListByDateShouldThrowExceptionForNullStartDate() throws UssException {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
				{
				@Override
				public List<Transaction> getAll() {
					return new ArrayList<>();
				}
				});
		
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		service.retrieveTransactionListByDate(null, endDate);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRetrieveTransactionListByDateShouldThrowExceptionForNullEndDate() throws UssException {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
				{
				@Override
				public List<Transaction> getAll() {
					return new ArrayList<>();
				}
				});
		
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-01");
		service.retrieveTransactionListByDate(startDate, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRetrieveTransactionListByDateShouldThrowExceptionForInvalidDates() throws UssException {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
				{
				@Override
				public List<Transaction> getAll() {
					return new ArrayList<>();
				}
				});
		
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-03");
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		service.retrieveTransactionListByDate(startDate, endDate);
	}
	
	@Test
	public void testRetrieveTransactionListByDateShouldReturnNothingForNoTransaction() throws UssException {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
				{
				@Override
				public List<Transaction> getAll() {
					return new ArrayList<>();
				}
				});
		
		Date startDate = UssCommonUtil.convertStringToDate("2013-01-01");
		Date endDate = UssCommonUtil.convertStringToDate("2013-01-02");
		List<Transaction> result = service.retrieveTransactionListByDate(startDate, endDate);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void testRetrieveTransactionListByDateShouldReturnForMatchingTransaction() throws UssException {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
				{
				@Override
				public List<Transaction> getAll() {
					List<Transaction> transactions = new ArrayList<>();
					
					try {
						transactions.add(new TestTransactionBuilder().withDate(UssCommonUtil.convertStringToDate("2016-01-01")).build());
						transactions.add(new TestTransactionBuilder().withDate(UssCommonUtil.convertStringToDate("2016-02-01")).build());
						transactions.add(new TestTransactionBuilder().withDate(UssCommonUtil.convertStringToDate("2016-03-01")).build());
						transactions.add(new TestTransactionBuilder().withDate(UssCommonUtil.convertStringToDate("2016-04-01")).build());
						transactions.add(new TestTransactionBuilder().withDate(UssCommonUtil.convertStringToDate("2016-05-01")).build());
					} catch (UssException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return transactions;
				}
				});
		
		Date startDate = UssCommonUtil.convertStringToDate("2016-02-01");
		Date endDate = UssCommonUtil.convertStringToDate("2016-04-01");
		List<Transaction> result = service.retrieveTransactionListByDate(startDate, endDate);
		assertNotNull(result);
		assertEquals(3, result.size());
	}
	
	@Test
	public void testCreateTransactionsShouldBeSave() throws UssException {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess());
		
		List<Product> products = new ArrayList<>();
		
		TestProductBuilder testProductBuilder = new TestProductBuilder();
		
		products.add(testProductBuilder.withProductID("CLO/1").build());
		products.add(testProductBuilder.withProductID("CLO/1").build());
		products.add(testProductBuilder.withProductID("CLO/2").build());
		products.add(testProductBuilder.withProductID("CLO/3").build());
		
		service.createTransactions(products, CheckOutService.PUBLIC_BUYER, UssCommonUtil.convertStringToDate("2016-02-01"));
		
		Date startDate = UssCommonUtil.convertStringToDate("2016-02-01");
		Date endDate = UssCommonUtil.convertStringToDate("2016-04-01");
		List<Transaction> result = service.retrieveTransactionListByDate(startDate, endDate);
		assertNotNull(result);
		assertEquals(3, result.size());
	}
	
	private class MockTransactionFileDataAccessWithoutFileAccess implements ITransactionDataAccess {
		
		private List<Transaction> transactions = new ArrayList<>();

		@Override
		public List<Transaction> getAll() {
			return transactions;
		}

		@Override
		public void create(List<Transaction> transactions) throws UssException {
			this.transactions.addAll(transactions);
		}
		
	}
	
}