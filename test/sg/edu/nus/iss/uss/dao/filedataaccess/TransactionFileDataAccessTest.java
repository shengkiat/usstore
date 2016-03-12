package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.TransactionDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccess;
import sg.edu.nus.iss.uss.model.Transaction;

public class TransactionFileDataAccessTest {
	
	private static final String TEST_DATA_DIR = System.getProperty("user.dir") + "\\test\\sg\\edu\\nus\\iss\\uss\\dao\\filedataaccess";
	private static final String TEST_FILE_NAME = "Transactions.dat";
	
	private TransactionDataAccess testDataAccess;
	
	@Test
	public void testCreateAndGetAllWhenThereIsNoDataForSingleTransaction() {
		testDataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		Date currentDate = new Date();
		List<Transaction> transactions = new ArrayList<>();
		
		transactions.add(new Transaction("CLO/1", "F42563743156", 2, currentDate));
		transactions.add(new Transaction("MUG/1", "F42563743156", 3, currentDate));
		
		for(Transaction transaction : transactions) {
			assertEquals(0, transaction.getTransactionID());
		}
		
		testDataAccess.create(transactions);
		
		List<Transaction> records = testDataAccess.getAll();
		assertEquals(2, records.size());
		
		for(Transaction transaction : records) {
			assertEquals(1, transaction.getTransactionID());
		}
	}
	
	@Test
	public void testCreateAndGetAllWhenThereIsNoDataForTwoTransaction() {
		testDataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		Date currentDate = new Date();
		List<Transaction> firstTransactions = new ArrayList<>();
		
		firstTransactions.add(new Transaction("CLO/1", "F42563743156", 2, currentDate));
		firstTransactions.add(new Transaction("MUG/1", "F42563743156", 3, currentDate));
		
		testDataAccess.create(firstTransactions);
		
		List<Transaction> secondTransactions = new ArrayList<>();
		
		secondTransactions.add(new Transaction("MUG/2", "F42563743156", 2, currentDate));
		secondTransactions.add(new Transaction("CLO/2", "F42563743156", 3, currentDate));
		
		testDataAccess.create(secondTransactions);
		
		List<Transaction> records = testDataAccess.getAll();
		assertEquals(4, records.size());
		
		int highestTransactionId = 0;
		
		for(Transaction transaction : records) {
			if (transaction.getTransactionID() > highestTransactionId) {
				highestTransactionId = transaction.getTransactionID();
			}
		}
		
		assertEquals(2, highestTransactionId);
	}
	
	@Test
	public void testCreateAndGetAllWhenThereIsData() {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(TEST_DATA_DIR + "\\" + TEST_FILE_NAME), "utf-8"))) {
			writer.write("1,CLO/1,F42563743156,2,2013-09-28\n");
			writer.write("1,MUG/1,F42563743156,3,2012-09-28\n");
			writer.write("2,STA/1,PUBLIC,1,2013-09-29\n");
			writer.write("3,STA/2,R64565FG4,2,2013-09-30\n");
		} 
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		testDataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		List<Transaction> records = testDataAccess.getAll();
		assertEquals(4, records.size());
		
		Date currentDate = new Date();
		List<Transaction> transactions = new ArrayList<>();
		
		Transaction transactionOne = new Transaction("CLO/1", "F42563743156", 2, currentDate);
		transactions.add(transactionOne);
		
		Transaction transactionTwo = new Transaction("MUG/1", "F42563743156", 3, currentDate);
		transactions.add(transactionTwo);
		
		for(Transaction transaction : transactions) {
			assertEquals(0, transaction.getTransactionID());
		}
		
		testDataAccess.create(transactions);
		
		records = testDataAccess.getAll();
		assertEquals(6, records.size());
		
		int highestTransactionId = 0;
		
		for(Transaction transaction : records) {
			if (transaction.getTransactionID() > highestTransactionId) {
				highestTransactionId = transaction.getTransactionID();
			}
		}
		
		assertEquals(4, highestTransactionId);
	}
	
	
	@Before
	public void setUp() throws IOException {
		Path path = getTestPath();

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("Test data file already exists: " + e.getMessage());
        }
	}
	
	@After
	public void tearDown() throws IOException {
		testDataAccess = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}
}
