package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
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

import sg.edu.nus.iss.uss.dao.ITransactionDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.TestTransactionBuilder;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.util.TestUtil;

public class TransactionFileDataAccessTest {
	
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Transactions.dat";
	
	private ITransactionDataAccess testDataAccess;
	
	@Test
	public void testCreateAndGetAllWhenThereIsNoDataForSingleTransaction() throws UssException {
		testDataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		List<Transaction> transactions = new ArrayList<>();
		
		transactions.add(new TestTransactionBuilder().build());
		transactions.add(new TestTransactionBuilder().build());
		
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
	public void testCreateAndGetAllWhenThereIsNoDataForTwoTransaction() throws UssException {
		testDataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		List<Transaction> firstTransactions = new ArrayList<>();
		
		firstTransactions.add(new TestTransactionBuilder().build());
		firstTransactions.add(new TestTransactionBuilder().build());
		
		testDataAccess.create(firstTransactions);
		
		List<Transaction> secondTransactions = new ArrayList<>();
		
		secondTransactions.add(new TestTransactionBuilder().build());
		secondTransactions.add(new TestTransactionBuilder().build());
		
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
	public void testCreateShouldWriteContentIntoFile() throws UssException {
		testDataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);

		List<Transaction> transactions = new ArrayList<>();
		
		transactions.add(new TestTransactionBuilder().build());
		transactions.add(new TestTransactionBuilder().build());
		
		for(Transaction transaction : transactions) {
			assertEquals(0, transaction.getTransactionID());
		}
		
		testDataAccess.create(transactions);
		
		List<String> records = getLinesFromFile();
		assertEquals(2, records.size());
	}
	
	@Test
	public void testCreateAndGetAllWhenThereIsData() throws IOException, UssException {
		
		TestUtil.createFileWithLines(TestUtil.getTestPath(TEST_FILE_NAME),  new String[] {
			"1,CLO/1,F42563743156,2,2013-09-28",
			"1,MUG/1,F42563743156,3,2012-09-28",
			"2,STA/1,PUBLIC,1,2013-09-29",
			"3,STA/2,R64565FG4,2,2013-09-30"
		});
		
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
	
	private List<String> getLinesFromFile() {
		List<String> result = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(getTestPath(), getCharsetForFile())) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	result.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
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
		
		TestUtil.destoryFile(TestUtil.getTestPath(TEST_FILE_NAME));
	}
	
	private Path getTestPath() {
		return Paths.get(TestUtil.getTestPath(TEST_FILE_NAME));
	}
	
	private Charset getCharsetForFile() {
		return Charset.forName("utf-8");
	}
}
