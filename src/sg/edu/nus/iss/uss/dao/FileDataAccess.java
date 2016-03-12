package sg.edu.nus.iss.uss.dao;

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
	}

}
