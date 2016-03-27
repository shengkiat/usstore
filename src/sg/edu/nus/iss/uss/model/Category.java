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
		//if (name.equals(null) ) {
			// throws Category description should not be empty
		//}
		this.name = name;
	}
	
	@Override
	public String toString() {//CLO,Clothing
        return code;

	}
	
	
}
