package sg.edu.nus.iss.uss.model;

public class TestCategoryBuilder {
	
	private String code = "CLO";
	private String name = "Clothing";
	
	
	public TestCategoryBuilder withCode(String code) {
		this.code = code;
		return this;
	}
	
	public TestCategoryBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Category build() {
		Category category = new Category(code, name);
		return category;
	}
	
}
