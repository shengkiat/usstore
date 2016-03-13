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
	protected void write(String[] arr) {
		
		Objects.requireNonNull(arr, "arr cannot be null");
		validateInput(arr);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathForFile().toAbsolutePath().toString(), true))) {
			StringBuilder builder = new StringBuilder();
			for(String content: arr) {
				builder.append(getContentDelimiter());
				builder.append(content);
			}
			writer.write(builder.toString().replaceFirst(getContentDelimiter(), ""));
			writer.newLine();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void validateInput(String[] arr) {
		for(String content: arr) {
			if (content != null && !content.equals("")) {
				return;
			}
		}
		
		throw new IllegalArgumentException("All arr content cannot be null or empty");
	}
	
	//TODO should throw custom exception?
	protected List<String> read(){
		List<String> result = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(getPathForFile(), getCharsetForFile())) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	result.add(line);
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

}
