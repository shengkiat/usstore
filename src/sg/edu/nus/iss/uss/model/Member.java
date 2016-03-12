package sg.edu.nus.iss.uss.model;


public class Member implements IBuyer{
	private String name;
	private String memberID;
	private int loyaltyPoint;
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}
	public void setLoyaltyPoint(int loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}
	
	@Override
	public String toString() {//Yan Martel,F42563743156,150
		//TODO toString
		
		return "";
	}

}
