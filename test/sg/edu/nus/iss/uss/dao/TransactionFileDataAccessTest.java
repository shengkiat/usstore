package sg.edu.nus.iss.uss.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.iss.uss.model.Transaction;

public class TransactionFileDataAccessTest {
	
	private static final String TEST_DATA_DIR = System.getProperty("user.dir") + "\\test\\sg\\edu\\nus\\iss\\uss\\dao";
	private static final String TEST_FILE_NAME = "Transactions.dat";
	
	@Test
	public void testGetAll() {
		IDataAccess<Transaction> dataAccess = new TransactionFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		List<Transaction> records = dataAccess.getAll();
	}
	
	@BeforeClass
	public static void setupBeforeClass() throws IOException {
		Path path = Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("Test data file already exists: " + e.getMessage());
        }
	}
	
	@AfterClass
	public static void setupAfterClass() throws IOException {
		Path path = Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
}
