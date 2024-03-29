package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.util.TestUtil;
import static org.junit.Assert.*;

public class FileDataAccessTest {
	
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Testing.dat";
	
	private FileDataAccess testDataAccess;
	
	@Test(expected=NullPointerException.class)
	public void testWriteNewLineShouldThrowExceptionForNullParameter() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		testDataAccess.writeNewLine(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWriteNewLineShouldThrowExceptionForNullContent() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[1];
		testDataAccess.writeNewLine(arr);
	}
	
	@Test
	public void testWriteNewLineShouldWriteCorrectlyIntoTheFileWithOneLine() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[2];
		arr[0] = "tester";
		arr[1] = "p12345678";
		
		testDataAccess.writeNewLine(arr);
		
		String expectedFileContent = "tester,p12345678";
		
		List<String> result = getLinesFromFile();
		
		assertEquals(1, result.size());
		assertEquals(expectedFileContent, result.get(0));
	}
	
	@Test
	public void testWriteNewLineShouldWriteCorrectlyIntoTheFileWithTwoLines() throws UssException {
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
		
		List<String> result = getLinesFromFile();
		
		assertEquals(2, result.size());
		assertEquals(expectedFileContentOne, result.get(0));
		assertEquals(expectedFileContentTwo, result.get(1));
	}
	
	@Test
	public void testWriteNewLineShouldWriteCorrectlyIntoTheFileEvenWhenThereIsNoNewLine() throws UssException, IOException {
		
		File file = new File(TestUtil.getTestPath(TEST_FILE_NAME));
		
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("tester,p12345678");
		//bw.newLine(); purposely do not add new line
		
		bw.close();
		
		testDataAccess = new FileDataAccessImpl();
		
		String[] arrOne = new String[2];
		arrOne[0] = "tester2";
		arrOne[1] = "p12345678";
		
		testDataAccess.writeNewLine(arrOne);
		
		List<String> result = getLinesFromFile();
		
		assertEquals(2, result.size());
	}
	
	@Test(expected=UssException.class)
	public void testWriteNewLineShouldThrowExceptionForRecordAlreadyExist() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arrOne = new String[2];
		arrOne[0] = "tester";
		arrOne[1] = "p12345678";
		
		testDataAccess.writeNewLine(arrOne);
		
		String[] arrTwo = new String[2];
		arrTwo[0] = "tester";
		arrTwo[1] = "this is for testing";
		
		testDataAccess.writeNewLine(arrTwo);
	}
	
	@Test(expected=NullPointerException.class)
	public void testOverwriteLineShouldThrowExceptionForNullParameter() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		testDataAccess.overwriteLine(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverwriteLineShouldThrowExceptionForNullContent() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[1];
		testDataAccess.overwriteLine(arr);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverwriteLineShouldThrowExceptionForNoMatchingPrimaryKey() throws UssException {
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
	public void testOverwriteLineShouldWriteCorrectlyIntoTheFileWithOneLine() throws UssException {
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
		
		List<String> result = getLinesFromFile();
		
		assertEquals(2, result.size());
		assertEquals(expectedFileContentOne, result.get(0));
		assertEquals(expectedFileContentTwo, result.get(1));
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveLineShouldThrowExceptionForNullParameter() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		testDataAccess.removeLine(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveLineShouldThrowExceptionForNullContent() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arr = new String[1];
		testDataAccess.removeLine(arr);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveLineShouldThrowExceptionForNoMatchingPrimaryKey() throws UssException {
		testDataAccess = new FileDataAccessImpl();
		
		String[] arrOne = new String[2];
		arrOne[0] = "tester";
		arrOne[1] = "p12345678";
		testDataAccess.writeNewLine(arrOne);
		
		String[] modifiedArr = new String[2];
		modifiedArr[0] = "tester1";
		modifiedArr[1] = "12345678";
		
		testDataAccess.removeLine(modifiedArr);
	}
	
	@Test
	public void testRemoveLineShouldWriteCorrectlyIntoTheFileWithOneLine() throws UssException {
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
		modifiedArr[1] = "p12345678";
		
		String expectedFileContentOne = "tester2,this is for testing";
		
		testDataAccess.removeLine(modifiedArr);
		
		List<String> result = getLinesFromFile();
		
		assertEquals(1, result.size());
		assertEquals(expectedFileContentOne, result.get(0));
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
		return Paths.get(TEST_DATA_DIR + File.separator + TEST_FILE_NAME);
	}
	
	private Charset getCharsetForFile() {
		return Charset.forName("utf-8");
	}
	
	private List<String> getLinesFromFile() {
		List<String> result = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(getTestPath(), getCharsetForFile())) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	result.add(line);
		    }
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	private class FileDataAccessImpl extends FileDataAccess {

		protected FileDataAccessImpl() throws UssException {
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

		@Override
		protected int getTotalNumberOfFields() {
			return 2;
		}
		
	}

}
