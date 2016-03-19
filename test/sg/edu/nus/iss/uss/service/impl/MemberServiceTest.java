package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.service.IMemberService;
import sg.edu.nus.iss.uss.service.impl.MemberService;
import sg.edu.nus.iss.uss.util.TestUtil;

public class MemberServiceTest {

	IMemberService service = null;
	
	@Before
	public void setUp() throws Exception {
		service = TestUtil.setUpMemberServiceWithThreeMember();
	}

	@After
	public void tearDown() throws Exception {
		TestUtil.destoryMemberServiceAndFile(service);
	}
	
	@Test
	public void testIsValidMember(){
		
		assertEquals(false, service.isValidMember("InvalidMemberID"));
	}

	@Test(expected=UssException.class)
	public void testUpdateNonExistMemberShouldThrowUssException() throws UssException {
		
		service.registerNewMember("Brian Earp", "T64565FG5");
		
		service.updateMemberLoyaltyPoint("NonExistMemberID", 100);
	}
	
	@Test(expected=UssException.class)
	public void testDeductPointsMoreThanMemberPointsShouldThrowUssException() throws UssException {
		
		service.deductMemberLoyltyPoint(300, "F42563743156");//member has only 150 points
	}
	
	@Test
	public void testAddPointsToNewMember() throws UssException {
		
		String newName = "Brian Earp";
		String newMemberID = "T64565FG5";
		int incPoint = 100;
		
		service.registerNewMember(newName, newMemberID);
		service.addMemberLoyaltyPoint(incPoint, newMemberID);//member has only 150 points
		
		Member member = service.getMemberByMemberID(newMemberID);
		
		assertEquals(incPoint, member.getLoyaltyPoint());
		
	}
	
	
	@Test
	public void testAddPointsToExistingMemberWithSomePoints() throws UssException {
	
		String exsitingMemberID = "X437F356";
		int incPoint = 100;
		int existingPoint = 250;
		
		service.addMemberLoyaltyPoint(incPoint, exsitingMemberID);//existing member has 250 points
		Member member = service.getMemberByMemberID(exsitingMemberID);
		
		assertEquals(incPoint+ existingPoint, member.getLoyaltyPoint());
	}
	

	@Test
	public void testRetrieveMembersAfterAddOneMember() throws UssException{
		List<Member> membersBefore = service.retrieveMemberList();
		
		assertEquals(3, membersBefore.size());
		
		service.registerNewMember("Brian Earp", "T64565FG5");
		
		List<Member> membersAfter = service.retrieveMemberList();
		
		assertEquals(4, membersAfter.size());
		
	}

}
