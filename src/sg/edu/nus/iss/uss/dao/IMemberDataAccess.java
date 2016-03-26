package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;

public interface IMemberDataAccess {
	
	public List<Member> getAll();
	public Member getMemberByMemberID(String memberID);
	public void create(Member member) throws UssException;
	public void update(Member member) throws UssException;
	public void createMembers(List<Member> members) throws UssException;
	public boolean isMemberExist(String memberID);
	public boolean isFirstPurchase(String memberID);
}
