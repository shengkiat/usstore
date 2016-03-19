package sg.edu.nus.iss.uss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.service.impl.TransactionService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;
import static org.junit.Assert.*;

public class TransactionServiceTest {
	
	@Test(expected=NullPointerException.class)
	public void testRetrieveTransactionListByDateShouldThrowExceptionForNullStartDate() throws UssException {
<<<<<<< HEAD
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
=======
		TransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
	public void testRetrieveTransactionListByDateShouldThrowExceptionForNullEndDate() {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
=======
	public void testRetrieveTransactionListByDateShouldThrowExceptionForNullEndDate() throws UssException {
		TransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
=======
		TransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
=======
		TransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
=======
		TransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess()
>>>>>>> refs/remotes/origin/master
				{
				@Override
				public List<Transaction> getAll() {
					List<Transaction> transactions = new ArrayList<>();
					
					transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-01-01")));
					transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-02-01")));
					transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-03-01")));
					transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-04-01")));
					transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-05-01")));
					
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
<<<<<<< HEAD
	public void testCreateTransactionsShouldBeSave() {
		ITransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess());
=======
	public void testCreateTransactionsShouldBeSave() throws UssException {
		TransactionService service = new TransactionService(new MockTransactionFileDataAccessWithoutFileAccess());
>>>>>>> refs/remotes/origin/master
		
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-01-01")));
		transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-02-01")));
		transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-03-01")));
		transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-04-01")));
		transactions.add(new Transaction("", "", 1, UssCommonUtil.convertStringToDate("2016-05-01")));
		
		service.createTransactions(transactions);
		
		Date startDate = UssCommonUtil.convertStringToDate("2016-02-01");
		Date endDate = UssCommonUtil.convertStringToDate("2016-04-01");
		List<Transaction> result = service.retrieveTransactionListByDate(startDate, endDate);
		assertNotNull(result);
		assertEquals(3, result.size());
	}
	
	private class MockTransactionFileDataAccessWithoutFileAccess extends TransactionFileDataAccess {
		
		public MockTransactionFileDataAccessWithoutFileAccess()
				throws UssException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void initialLoad() {
			//do nothing
		}
	}
	
}