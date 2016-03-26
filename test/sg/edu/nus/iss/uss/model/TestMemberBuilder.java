package sg.edu.nus.iss.uss.model;

public class TestMemberBuilder {
	
	private String name;
	private String memberID ;
	private int loyaltyPoint;
	
	
	
	public TestMemberBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public TestMemberBuilder withMemberID(String memberID) {
		this.memberID = memberID;
		return this;
	}
	
	public TestMemberBuilder withLoyaltyPoint(int loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
		return this;
	}
	
	public Member build() {
		Member member = new Member(name, memberID, loyaltyPoint);
		return member;
	}
	
}
