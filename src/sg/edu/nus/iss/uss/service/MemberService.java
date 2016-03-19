package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.dao.MemberDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;

import java.util.List;

public class MemberService extends UssCommonService {
	public final static int INIT_LOYALTY_POINT = -1;
	
	private MemberDataAccess memberDataAccess = null;
	
	public MemberService(MemberDataAccess memberDataAccess) {
		super();
		setMemberDataAccess(memberDataAccess);
	}
	
	private MemberDataAccess getMemberDataAccess() {
		return memberDataAccess;
	}

	private void setMemberDataAccess(MemberDataAccess memberDataAccess) {
		this.memberDataAccess = memberDataAccess;
	}

	public List<Member> retrieveMemberList(){
		
		return getMemberDataAccess().getAll();
		
	}
	
	public Member getMemberByMemberID(String memberID){
		
		return getMemberDataAccess().getMemberByMemberID(memberID);
		
	}
	
	public void registerNewMember(String name, String idCardNumber) throws UssException{
		
		Member member = new Member(name, idCardNumber, INIT_LOYALTY_POINT);
		
		getMemberDataAccess().create(member);;
	}
	
	public void updateMemberLoyaltyPoint(String memberID, int point) throws UssException{
		Member member = getMemberDataAccess().getMemberByMemberID(memberID);
		
		if(member != null){
			member.setLoyaltyPoint(point);
		}
		getMemberDataAccess().update(member);
	}
	
	public void addMemberLoyaltyPoint(int point, String memberID) throws UssException{
		
		Member member = getMemberDataAccess().getMemberByMemberID(memberID);
		
		if(member != null){
			int existingPoint = member.getLoyaltyPoint();
			
			if(existingPoint == -1){
				member.setLoyaltyPoint(point);
			}else{
				member.setLoyaltyPoint(point + existingPoint);
			}
		}
		
		getMemberDataAccess().update(member);
		
	}
	
	public void deductMemberLoyltyPoint(int point, String memberID) throws UssException{
		
		Member member = getMemberDataAccess().getMemberByMemberID(memberID);
		int existingPoint = member.getLoyaltyPoint();
		
		if(existingPoint == -1 || (existingPoint - point) < 0){
			throw new UssException(ErrorConstants.UssCode.MEMBER, ErrorConstants.NOT_ENOUGH_POINTS);
		}else{
			member.setLoyaltyPoint(existingPoint - point);
		}
		
		getMemberDataAccess().update(member);
	}
	
    public boolean isValidMember(String memberID) {

        return getMemberDataAccess().isMemberExist(memberID);
    }
    
    public boolean isFirstPurpose(String memberID){
    	
    	//TODO 
    	return false;
    }

}
