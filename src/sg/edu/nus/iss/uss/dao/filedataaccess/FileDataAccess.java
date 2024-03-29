package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;

abstract class FileDataAccess {
	
	
	private final String fileName;
	private final String directory;
	
	public FileDataAccess(String fileName) throws UssException {
		this(fileName, "data");
	}
	
	protected FileDataAccess(String fileName, String directory) throws UssException {
		Objects.requireNonNull(fileName, "fileName cannot be null");
		Objects.requireNonNull(directory, "directory cannot be null");
		
		this.fileName = fileName;
		this.directory = directory;
		
		initialLoad();
	}
	
	protected void writeNewLine(String[] arr) throws UssException {
		validateInput(arr);
		
		List<String[]> existingContents = readAll();
		
		if (isValidatingRecordFoundRequiredWhenWriteNewLine() && isRecordFound(arr, existingContents)) {
			throw new UssException(ErrorConstants.UssCode.DAO, "Record already exist using key, " + getPrimaryKey(arr));
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathForFile().toAbsolutePath().toString(), false))) {
			
			//Rewrite existing contents to prevent file being manually amended and no new line
			for(String[] existingContent : existingContents) {
				writer.write(generateWriteContent(existingContent));
				writer.newLine();
			}
			
			writer.write(generateWriteContent(arr));
			writer.newLine();
		} 
		
		catch (IOException e) {
			throw new UssException(ErrorConstants.UssCode.DAO, e);
		}
	}
	
	protected void overwriteLine(String[] arr) throws UssException {
		
		validateInput(arr);
		
		List<String[]> existingContents = readAll();
		
		validateRecordExist(arr, existingContents);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathForFile().toAbsolutePath().toString(), false))) {
			
			for(String[] existingContent : existingContents) {
				String toWriteContent = getPrimaryKey(arr).equals(getPrimaryKey(existingContent)) 
										? generateWriteContent(arr) : generateWriteContent(existingContent);
				writer.write(toWriteContent);
				writer.newLine();
			}
			
		} 
		
		catch (IOException e) {
			throw new UssException(ErrorConstants.UssCode.DAO, e);
		}
	}
	
	protected void removeLine(String[] arr) throws UssException {
		validateInput(arr);
		
		List<String[]> existingContents = readAll();
		
		validateRecordExist(arr, existingContents);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathForFile().toAbsolutePath().toString(), false))) {
			
			for(String[] existingContent : existingContents) {
				if (!getPrimaryKey(arr).equals(getPrimaryKey(existingContent))) {
					writer.write(generateWriteContent(existingContent));
					writer.newLine();
				}
			}
		} 
		
		catch (IOException e) {
			throw new UssException(ErrorConstants.UssCode.DAO, e);
		}
	}
	
	private void validateInput(String[] arr) throws UssException {
		Objects.requireNonNull(arr, "arr cannot be null");
		
		if (arr.length != getTotalNumberOfFields()) {
			throw new IllegalArgumentException("Expected total number of fields: " + getTotalNumberOfFields() + " but: " + arr.length);
		}
		
		for(String content: arr) {
			if (content != null && !content.equals("")) {
				return;
			}
		}
		
		throw new UssException(ErrorConstants.UssCode.DAO, ErrorConstants.EMPTY_CONTENT);
	}
	
	private void validateRecordExist(String[] arr, List<String[]> existingContents) {
		if (!isRecordFound(arr, existingContents)) {
			throw new IllegalArgumentException("Unable to found existing content to overwrite using " + getPrimaryKey(arr));
		}
	}
	
	private boolean isRecordFound(String[] arr, List<String[]> existingContents) {
		Objects.requireNonNull(arr, "arr cannot be null");
		
		for(String[] existingContent: existingContents) {
			if (getPrimaryKey(arr).equals(getPrimaryKey(existingContent))) {
				return true;
			}
		}
		
		return false;
	}
	
	private String generateWriteContent(String[] arr) {
		StringBuilder builder = new StringBuilder();
		for(String content: arr) {
			builder.append(getContentDelimiter());
			builder.append(content);
		}
		
		return builder.toString().replaceFirst(getContentDelimiter(), "");
	}
	
	protected List<String[]> readAll(){
		return readAll(getPathForFile());
	}
	
	protected List<String[]> readAll(Path pathForFile){
		List<String[]> result = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(pathForFile, getCharsetForFile())) {
		    String line = null;

		    while ((line = reader.readLine()) != null) {
		    	String[] arr = line.split(getContentDelimiter());
		    	result.add(arr);
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
	
	protected abstract void initialLoad() throws UssException;
	
	protected abstract int getTotalNumberOfFields();
	
	protected abstract String getPrimaryKey(String[] arr);
	
	protected boolean isValidatingRecordFoundRequiredWhenWriteNewLine() {
		return true;
	}

}
