package sg.edu.nus.iss.uss.service;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.MemberFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;

public class MemberServiceTest {
	private static final String TEST_DATA_DIR;
	private static final String TEST_FILE_NAME = "Members.dat";
	
	static {
		StringBuilder builder = new StringBuilder(System.getProperty("user.dir"));
		builder.append(File.separator).append("test");
		builder.append(File.separator).append("sg");
		builder.append(File.separator).append("edu");
		builder.append(File.separator).append("nus");
		builder.append(File.separator).append("iss");
		builder.append(File.separator).append("uss");
		builder.append(File.separator).append("service");
		TEST_DATA_DIR =  builder.toString();
	}

	MemberService service = null;
	
	@Before
	public void setUp() throws Exception {
		initFileWithThreeMembers();
		service = new MemberService(new MemberFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR));
	}

	@After
	public void tearDown() throws Exception {
		service = null;
		destoryFile();
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
	public void testRetrieveMembersAfterAddOneMember(){
		List<Member> membersBefore = service.retrieveMemberList();
		
		assertEquals(3, membersBefore.size());
		
		service.registerNewMember("Brian Earp", "T64565FG5");
		
		List<Member> membersAfter = service.retrieveMemberList();
		
		assertEquals(4, membersAfter.size());
		
	}
	
	
	private void initFileWithThreeMembers() throws IOException{
		/*
		 * Create Members.dat file with three members
		 * 
		 */
		
		String [] lines = new String []{
			"Yan Martel,F42563743156,150",
			"Suraj Sharma,X437F356,250",
			"Ang Lee,R64565FG4,-1"
		};

		createFileWithLines(TEST_DATA_DIR+ File.separator+ TEST_FILE_NAME, lines);
		
	}

	private void createFileWithLines(String fileName, String[] lines) throws IOException {
//		File file = new File("C:\\NUS\\project\\usstore\\test\\sg\\edu\\nus\\iss\\uss\\service\\Members.dat");

		File file = new File(fileName);
		
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(String line : lines){
			bw.write(line);
			bw.newLine();
		}
		
		bw.close();
		
	}
	
	private void destoryFile(){
		

		File file = new File(TEST_DATA_DIR+ File.separator+ TEST_FILE_NAME);

		// if file doesnt exists, then create it
		if (file.exists()) {
			file.delete();
		}
	}
	

}
