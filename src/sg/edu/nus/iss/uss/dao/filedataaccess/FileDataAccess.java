package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class FileDataAccess {
	
	
	private final String fileName;
	private final String directory;
	
	public FileDataAccess(String fileName) {
		this(fileName, "data");
	}
	
	protected FileDataAccess(String fileName, String directory) {
		Objects.requireNonNull(fileName, "fileName cannot be null");
		Objects.requireNonNull(directory, "directory cannot be null");
		
		this.fileName = fileName;
		this.directory = directory;
		
		initialLoad();
	}
	
	//TODO should throw custom exception?
	protected void writeNewLine(String[] arr) {
		validateInput(arr);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathForFile().toAbsolutePath().toString(), true))) {
			writer.write(generateWriteContent(arr));
			writer.newLine();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void overwriteLine(String[] arr) {
		
		validateInput(arr);
		
		List<String[]> existingContents = readAll();
		
		validatePrimaryKeyFound(arr, existingContents);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathForFile().toAbsolutePath().toString(), false))) {
			
			for(String[] existingContent : existingContents) {
				String toWriteContent = getPrimaryKey(arr).equals(getPrimaryKey(existingContent)) 
										? generateWriteContent(arr) : generateWriteContent(existingContent);
				writer.write(toWriteContent);
				writer.newLine();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void validateInput(String[] arr) {
		Objects.requireNonNull(arr, "arr cannot be null");
		
		if (arr.length != getTotalNumberOfFields()) {
			throw new IllegalArgumentException("Expected total number of fields: " + getTotalNumberOfFields() + " but: " + arr.length);
		}
		
		for(String content: arr) {
			if (content != null && !content.equals("")) {
				return;
			}
		}
		
		throw new IllegalArgumentException("All arr content cannot be null or empty");
	}
	
	private void validatePrimaryKeyFound(String[] arr, List<String[]> existingContents) {
		Objects.requireNonNull(arr, "arr cannot be null");
		
		for(String[] existingContent: existingContents) {
			if (getPrimaryKey(arr).equals(getPrimaryKey(existingContent))) {
				return;
			}
		}
		
		throw new IllegalArgumentException("Unable to found existing content to overwrite using " + getPrimaryKey(arr));
	}
	
	private String generateWriteContent(String[] arr) {
		StringBuilder builder = new StringBuilder();
		for(String content: arr) {
			builder.append(getContentDelimiter());
			builder.append(content);
		}
		
		return builder.toString().replaceFirst(getContentDelimiter(), "");
	}
	
	//TODO should throw custom exception?
	protected List<String[]> readAll(){
		List<String[]> result = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(getPathForFile(), getCharsetForFile())) {
		    String line = null;
		    int lineNo = 1;
		    while ((line = reader.readLine()) != null) {
		    	String[] arr = line.split(getContentDelimiter());
		    	
		    	//TODO should have validation?
		    	//if (arr.length != getTotalNumberOfFields()) {
					//throw new IllegalArgumentException(fileName + ", Line " + lineNo + ", Expected total number of fields: " + getTotalNumberOfFields() + " but: " + arr.length);
				//}
		    	result.add(arr);
		    	lineNo++;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Path getPathForFile() {
		return Paths.get(directory + File.separator + fileName);
	}
	
	private Charset getCharsetForFile() {
		return Charset.forName("utf-8");
	}
	
	protected String getContentDelimiter() {
		return ",";
	}
	
	protected String getDirectory() {
		return directory;
	}
	
	protected String getFileName() {
		return fileName;
	}
	
	protected abstract void initialLoad();
	
	protected abstract int getTotalNumberOfFields();
	
	protected abstract String getPrimaryKey(String[] arr);

}
