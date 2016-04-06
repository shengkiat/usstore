package sg.edu.nus.iss.uss.model;

public class StoreKeeper implements IUser{
	
	private String name;
	private String password;
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public StoreKeeper(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
	
}
