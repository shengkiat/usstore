package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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

		try (Writer writer = Files.newBufferedWriter(getPathForFile(), getCharsetForFile())) {
			for(String content: arr) {
				writer.write(content);
				writer.write(getContentDelimiter());
			}
			writer.write("\n");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return Paths.get(directory + "/" + fileName);
	}
	
	private Charset getCharsetForFile() {
		return Charset.forName("utf-8");
	}
	
	protected String getContentDelimiter() {
		return ",";
	}
	
	protected abstract void initialLoad();

}
