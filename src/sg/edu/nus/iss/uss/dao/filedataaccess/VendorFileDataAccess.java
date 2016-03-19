package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.uss.dao.IVendorDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Vendor;

public class VendorFileDataAccess extends FileDataAccess implements IVendorDataAccess {

	private Map<String, List<Vendor>> vendorMap;

	public VendorFileDataAccess() throws UssException {
		super("Vendors%s.dat");
	}

	VendorFileDataAccess(String fileName, String directory) throws UssException {
		super(fileName, directory);
	}

	@Override
	public Map<String, List<Vendor>> getAll() {
	
			return vendorMap;
		
	}

	@Override
	public void create(Vendor vendor) {
		Path path = Paths.get(getDirectory() + File.separator + String.format(getFileName(), vendor.getCategory()));

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
		
		this.vendorMap = new HashMap<String, List<Vendor>>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(getDirectory()), "Vendors*.dat")) {
			for (Path file : stream) {
				List<String[]> contents = readAll(file);

				String categoryCode = file.toString().replace(getDirectory() + File.separator + "Vendors", "");
				categoryCode = categoryCode.replace(".dat", "");

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

					if (this.vendorMap.containsKey(categoryCode)) {
						this.vendorMap.get(categoryCode).add(vendor);
					} else {
						this.vendorMap.put(categoryCode, new ArrayList<Vendor>());
						this.vendorMap.get(categoryCode).add(vendor);
					}

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
		throw new RuntimeException("not implemented yet");
	}

	@Override
	protected int getTotalNumberOfFields() {
		return 0;
	}
}
