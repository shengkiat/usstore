package sg.edu.nus.iss.uss.model;

import java.io.File;

public class UniversitySouvenirStore implements IStore {
	
	private String storeName;
	private String storeDescription;
	
	@Override
	public void InitStore() {
		File file = new File("");//TODO
		setStoreInfoFromFile(file);
	}
	
	@Override
	public String getStoreName() {
		return storeName;
	}

	@Override
	public String getStoreDescription() {
		return storeDescription;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}
	
	private void setStoreInfoFromFile(File inputFile){
		//TODO
	}

	
	
}
