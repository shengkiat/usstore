package sg.edu.nus.iss.uss.service.impl;

import sg.edu.nus.iss.uss.dao.IMemberDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.service.IMemberService;

import java.util.List;

public class MemberService extends UssCommonService implements IMemberService{
	public final static int INIT_LOYALTY_POINT = -1;
	
	private IMemberDataAccess memberDataAccess = null;
	
	public MemberService(IMemberDataAccess memberDataAccess) {
		super();
		setMemberDataAccess(memberDataAccess);
	}
	
	private IMemberDataAccess getMemberDataAccess() {
		return memberDataAccess;
	}

	private void setMemberDataAccess(IMemberDataAccess memberDataAccess) {
		this.memberDataAccess = memberDataAccess;
	}
	
	@Override
	public List<Member> retrieveMemberList(){
		
		return getMemberDataAccess().getAll();
		
	}
	
	@Override
	public Member getMemberByMemberID(String memberID){
		
		return getMemberDataAccess().getMemberByMemberID(memberID);
		
	}
	
	@Override
	public void registerNewMember(String name, String idCardNumber) throws UssException{
		
		Member member = new Member(name, idCardNumber, INIT_LOYALTY_POINT);
		
		getMemberDataAccess().create(member);;
	}
	
	@Override
	public void updateMemberLoyaltyPoint(String memberID, int point) throws UssException{
		Member member = getMemberDataAccess().getMemberByMemberID(memberID);
		
		if(member != null){
			member.setLoyaltyPoint(point);
		}
		getMemberDataAccess().update(member);
	}
	
	@Override
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
	
	@Override
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
	
	@Override
    public boolean isValidMember(String memberID) {

        return getMemberDataAccess().isMemberExist(memberID);
    }
    
	@Override
    public boolean isFirstPurpose(String memberID){
    	
    	//TODO 
    	return false;
    }

}
