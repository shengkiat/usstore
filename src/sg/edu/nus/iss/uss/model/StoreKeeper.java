package sg.edu.nus.iss.uss.model;

public class StoreKeeper implements IUser{
	
	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {//Stacy,Dean56s
		//TODO toString
		
		return "";
	}
	
	
	public StoreKeeper(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
	
}
