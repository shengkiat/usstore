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
	
		this.name = name;
	}
	
	@Override
	public String toString() {//CLO,Clothing
		StringBuilder sb = new StringBuilder();
		if (this.code != null) {
			sb.append(this.code);
			if (this.name == null) {
				this.name = "";}
			else {
				sb.append(",");
				sb.append(this.name);}
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		int result = hashcode;
		String str;
		if (result ==0 ) {
		    result = 17;
		    str = this.code;
			result = 31 * result + str.hashCode();
			str = this.name;
			result = 31 * result + str.hashCode();
			}
			hashcode = result;
			return result;
		}
	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Category)) return false;
		Category category = (Category) o;
		
	    return (category.code == this.code) && (category.name == this.name);

	}
	
}
