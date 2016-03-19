package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;

public interface MemberDataAccess {
	
	public List<Member> getAll();
	public Member getMemberByMemberID(String memberID);
	public void create(Member member);
	public void update(Member member) throws UssException;
	public void createMembers(List<Member> members);
	public boolean isMemberExist(String memberID);
}
