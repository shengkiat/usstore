package sg.edu.nus.iss.uss.model;

public class Category {
	private String code;
	private String name;
	
	public Category() {}
	
	public Category (String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if (code.length() != 3) {
			// throws length is not equal to 3
		} else
		{
		   this.code = code;
		   }
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	
	@Override
	public String toString() {//CLO,Clothing
		StringBuilder sb = new StringBuilder();
		if (this.code == null) {
			sb.append(this.code);
			if (this.name == null) {
				this.name = "";}
			else {
				sb.append(",");
				sb.append(this.name);}
		}
		
		return sb.toString();

	}
	
	
}
