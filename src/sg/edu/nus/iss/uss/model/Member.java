package sg.edu.nus.iss.uss.model;


public class Member implements Comparable<Member>{
	private String name;
	private String memberID;
	private int loyaltyPoint;
	
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
	
	public Member(String name, String memberID, int loyaltyPoint) {
		super();
		this.name = name;
		this.memberID = memberID;
		this.loyaltyPoint = loyaltyPoint;
	}

	@Override
	public String toString() {//Yan Martel,F42563743156,150
		StringBuffer sb = new StringBuffer();

		if(null != name){
		  sb.append(name);
		}
		
		sb.append(",");
		
		if(memberID != null){
		  sb.append(this.memberID);
		}
		
		sb.append(",");
		
		sb.append(this.loyaltyPoint);
		
		return sb.toString();
	}

	@Override
	public int compareTo(Member o) {
		
		return	this.memberID.compareTo(o.getMemberID());
	}

}
