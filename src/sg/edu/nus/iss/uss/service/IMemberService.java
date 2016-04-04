package sg.edu.nus.iss.uss.service;

import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;

public interface IMemberService {

	public List<Member> retrieveMemberList();

	public Member getMemberByMemberID(String memberID);

	public void registerNewMember(String name, String idCardNumber) throws UssException;

	public void addMemberLoyaltyPoint(int point, String memberID) throws UssException;

	public void deductMemberLoyltyPoint(int point, String memberID) throws UssException;

	public boolean isValidMember(String memberID);

	public boolean isFirstPurchase(String memberID);

}
