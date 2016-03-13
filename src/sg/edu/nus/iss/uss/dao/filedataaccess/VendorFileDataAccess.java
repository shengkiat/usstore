package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.VendorDataAccess;
import sg.edu.nus.iss.uss.model.Vendor;

public class VendorFileDataAccess extends FileDataAccess implements VendorDataAccess {

	public VendorFileDataAccess() {
		super("Vendors%s.dat");
	}
	
	VendorFileDataAccess(String fileName, String directory) {
		super(fileName, directory);	
	}

	@Override
	public Map<String, List<Vendor>> getAll() {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(getDirectory()), "Vendors*.dat")) {
			for (Path file : stream) {
                List<String[]> contents = readAll(file);
                //TODO
            }
	     } 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(Vendor vendor) {
		Path path = Paths.get(getDirectory() + "/" + String.format(getFileName(), vendor.getCategory()));

        try {
        	Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("file already exists: " + e.getMessage());
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initialLoad() {
		
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return 0;
	}
}
