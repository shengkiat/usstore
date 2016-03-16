package sg.edu.nus.iss.uss.service;

import sg.edu.nus.iss.uss.model.Member;

import java.util.List;

public class MemberService extends UssCommonService {

	public List<Member> retrieveMemberList(){
		//TODO
		
		return null;
	}
	
	public Member registerNewMember(String name, String idCardNumber){
		//TODO 
		
		return null;
	}
	
	public Member updateMemberLoyaltyPoint(int point){
		//TODO
		
		return null;
	}

    // added by Jia Cheng on 15/03/2016
    public boolean isValidMember(String memberID) {

        return false;
    }

}
