package sg.edu.nus.iss.uss.model;

public class Category {
	private String code;
	private String name;
	
	private volatile int hashcode;
	
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
		if (name.equals(null) ) {

		}
		//if (name.equals(null) ) {
			// throws Category description should not be empty
		//}

		this.name = name;
	}
	
	@Override
	public String toString() {//CLO,Clothing
        //System.out.println(code);
		StringBuilder sb = new StringBuilder();
		sb.append(this.code);
		sb.append(",");
		sb.append(this.name);
		
		/*System.out.println("");
		System.out.println(sb.toString());*/
		return sb.toString();

	}
	
	
	@Override
	public int hashCode() {
		int result = hashcode;
		if (result ==0 ) {
		    result = 17;	
			result = 31 * result + this.code.hashCode();
			result = 31 * result + this.name.hashCode();
			hashcode = result;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Category)) return false;
		Category c = (Category) o;
		
	    return c.code == this.code
	    && c.name == this.name;
	    
	}
	
	
}
