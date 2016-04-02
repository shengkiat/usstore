package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.IVendorDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Vendor;

public class VendorFileDataAccess extends FileDataAccess implements IVendorDataAccess {

	private Map<String, List<Vendor>> vendors;

	public VendorFileDataAccess() throws UssException {
		super("Vendors%s.dat");
	}

	VendorFileDataAccess(String fileName, String directory) throws UssException {
		super(fileName, directory);
	}

	@Override
	public Map<String, List<Vendor>> getAll() {
		return vendors;
	}

//	@Override
//	public void create(Vendor vendor) throws UssException {
//		Path path = Paths.get(getDirectory() + File.separator + String.format(getFileName(), vendor.getCategory()));
//
//		try {
//			
//			if (!Files.exists(path.getParent())) {
//				Files.createDirectories(path.getParent());
//				Files.createFile(path);
//			}
//			
//			String categoryCode = vendor.getCategory().getCode();
//			
//			addIntoVendors(vendor, categoryCode);
//			
//			
//		} catch (FileAlreadyExistsException e) {
//			throw new RuntimeException("file already exists: " + e.getMessage());
//		} catch (IOException e) {
//			throw new UssException(ErrorConstants.UssCode.DAO, e);
//		}
//	}

	@Override
	protected void initialLoad() throws UssException {
		
		this.vendors = new HashMap<String, List<Vendor>>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(getDirectory()), "Vendors*.dat")) {
			for (Path file : stream) {
				List<String[]> contents = readAll(file);

				String categoryCode = file.toString().replace(getDirectory() + File.separator + "Vendors", "");
				categoryCode = categoryCode.replace(".dat", "");
				
				if (contents.size() <= 3) {
					throw new UssException(ErrorConstants.UssCode.VENDOR, String.format(ErrorConstants.LESS_THAN_SPECIFLED_NO_OF_RECORDS, categoryCode));
				}

				// Category Code must be 3 letters
				if (categoryCode.length() != 3) {
					continue;
				}

				for (int i = 0; i < contents.size(); i++) {
					String[] line = contents.get(i);

					Vendor vendor = new Vendor();
					vendor.setPreference(i);

					vendor.setName(line[0]);
					vendor.setDescription(line[1]);

					addIntoVendors(vendor, categoryCode);
				}
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[0];
	}

	@Override
	protected int getTotalNumberOfFields() {
		return 0;
	}
	
	private void addIntoVendors(Vendor vendor, String categoryCode) {
		if (this.vendors.containsKey(categoryCode)) {
			this.vendors.get(categoryCode).add(vendor);
		} else {
			this.vendors.put(categoryCode, new ArrayList<Vendor>());
			this.vendors.get(categoryCode).add(vendor);
		}
	}
}
