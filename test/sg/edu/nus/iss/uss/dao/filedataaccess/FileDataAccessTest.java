package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileDataAccessTest {
	
	private static final String TEST_DATA_DIR = System.getProperty("user.dir") + "\\test\\sg\\edu\\nus\\iss\\uss\\dao\\filedataaccess";
	private static final String TEST_FILE_NAME = "Testing.dat";
	
	private FileDataAccess testDataAccess;
	
	@Test(expected=NullPointerException.class)
	public void testWriteNewLineShouldThrowExceptionForNullParameter() {
		testDataAccess = new FileDataAccessImpl();
		testDataAccess.writeNewLine(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWriteNewLineShouldThrowExceptionForNullContent() {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[1];
		testDataAccess.writeNewLine(arr);
	}
	
	@Test
	public void testWriteNewLineShouldWriteCorrectlyIntoTheFileWithOneLine() {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[2];
		arr[0] = "tester";
		arr[1] = "p12345678";
		
		testDataAccess.writeNewLine(arr);
		
		String expectedFileContent = "tester,p12345678";
		
		List<String> result = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(getTestPath(), getCharsetForFile())) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	result.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, result.size());
		assertEquals(expectedFileContent, result.get(0));
	}
	
	@Test
	public void testWriteNewLineShouldWriteCorrectlyIntoTheFileWithTwoLines() {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arrOne = new String[2];
		arrOne[0] = "tester";
		arrOne[1] = "p12345678";
		
		testDataAccess.writeNewLine(arrOne);
		
		String[] arrTwo = new String[2];
		arrTwo[0] = "tester2";
		arrTwo[1] = "this is for testing";
		
		testDataAccess.writeNewLine(arrTwo);
		
		String expectedFileContentOne = "tester,p12345678";
		String expectedFileContentTwo = "tester2,this is for testing";
		
		List<String> result = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(getTestPath(), getCharsetForFile())) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	result.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(2, result.size());
		assertEquals(expectedFileContentOne, result.get(0));
		assertEquals(expectedFileContentTwo, result.get(1));
	}
	
	@Test(expected=NullPointerException.class)
	public void testOverwriteLineShouldThrowExceptionForNullParameter() {
		testDataAccess = new FileDataAccessImpl();
		testDataAccess.overwriteLine(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverwriteLineShouldThrowExceptionForNullContent() {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[1];
		testDataAccess.overwriteLine(arr);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverwriteLineShouldThrowExceptionForNoMatchingPrimaryKey() {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arrOne = new String[2];
		arrOne[0] = "tester";
		arrOne[1] = "p12345678";
		testDataAccess.writeNewLine(arrOne);
		
		String[] modifiedArr = new String[2];
		modifiedArr[0] = "tester1";
		modifiedArr[1] = "12345678";
		
		testDataAccess.overwriteLine(modifiedArr);
	}
	
	@Test
	public void testOverwriteLineShouldWriteCorrectlyIntoTheFileWithOneLine() {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arrOne = new String[2];
		arrOne[0] = "tester";
		arrOne[1] = "p12345678";
		testDataAccess.writeNewLine(arrOne);
		
		String[] arrTwo = new String[2];
		arrTwo[0] = "tester2";
		arrTwo[1] = "this is for testing";
		testDataAccess.writeNewLine(arrTwo);
		
		String[] modifiedArr = new String[2];
		modifiedArr[0] = "tester";
		modifiedArr[1] = "12345678";
		
		String expectedFileContentOne = "tester,12345678";
		String expectedFileContentTwo = "tester2,this is for testing";
		
		testDataAccess.overwriteLine(modifiedArr);
		
		List<String> result = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(getTestPath(), getCharsetForFile())) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	result.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(2, result.size());
		assertEquals(expectedFileContentOne, result.get(0));
		assertEquals(expectedFileContentTwo, result.get(1));
	}
	
	@After
	public void tearDown() throws IOException {
		testDataAccess = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
	private Path getTestPath() {
		return Paths.get(TEST_DATA_DIR + File.separator + TEST_FILE_NAME);
	}
	
	private Charset getCharsetForFile() {
		return Charset.forName("utf-8");
	}
	
	private class FileDataAccessImpl extends FileDataAccess {

		protected FileDataAccessImpl() {
			super(TEST_FILE_NAME, TEST_DATA_DIR);
		}

		@Override
		protected void initialLoad() {
			//do nothing
		}

		@Override
		protected String getPrimaryKey(String[] arr) {
			return arr[0];
		}
		
	}

}
