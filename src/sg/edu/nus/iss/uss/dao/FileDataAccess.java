package sg.edu.nus.iss.uss.dao;

abstract class FileDataAccess {
	
	private final String fileName;
	private final String directory;
	
	public FileDataAccess(String fileName) {
		this(fileName, "data");
	}
	
	public FileDataAccess(String fileName, String directory) {
		this.fileName = fileName;
		this.directory = directory;
	}

}
