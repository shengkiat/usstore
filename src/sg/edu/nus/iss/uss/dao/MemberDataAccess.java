package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Member;

public interface MemberDataAccess {
	
	public List<Member> getAll();
	public void create(Member member);
	public void update(Member member);
}
