package sg.edu.nus.iss.uss.model;

public class Vendor {
	private String name;
	private String description;
	private int preference;
	private Category category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPreference() {
		return preference;
	}
	public void setPreference(int preference) {
		this.preference = preference;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {//Nancy’s Gifts,Best of the best gifts from Nancy’s
		//TODO toString
		
		return "";
	}
	
}
