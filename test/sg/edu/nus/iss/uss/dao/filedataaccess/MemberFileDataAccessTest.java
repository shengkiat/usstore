package sg.edu.nus.iss.uss.dao.filedataaccess;

import static org.junit.Assert.*;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.uss.dao.MemberDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.util.TestUtil;

public class MemberFileDataAccessTest {
	private static final String TEST_DATA_DIR = TestUtil.getTestDirectoryForFile();
	private static final String TEST_FILE_NAME = "Members.dat";
	
	private MemberDataAccess testDataAccess;
	

	@Before
	public void setUp() throws Exception {
		Path path = getTestPath();

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("Test data file already exists: " + e.getMessage());
        }
	}

	@After
	public void tearDown() throws Exception {
		testDataAccess = null;
		
		Path path = getTestPath();

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
	}
	
//	@Test
//	public void testGetAllMember(){
//		testDataAccess = new MemberFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
//		List<Member> members = testDataAccess.getAll();
//		
//		assertEquals(3, members.size());
//	}
//	
	
	@Test
	public void testCreateAndGetAllForOneMember_CompareMemeberDetail() throws UssException {
		testDataAccess = new MemberFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		Member member1 = new Member("Yan Martel", "F42563743156", 150);
		
		testDataAccess.create(member1);
		
		List<Member> members = testDataAccess.getAll();
		
		assertEquals(1, members.size());
		
		Member memberFromFile = members.get(0);
		
		assertEquals(member1.getName(), memberFromFile.getName());
		assertEquals(member1.getMemberID(), memberFromFile.getMemberID());
		assertEquals(member1.getLoyaltyPoint(), memberFromFile.getLoyaltyPoint());
		
	}
	
	@Test
	public void testCreateAndGetAllForThreeMembers() throws UssException {
		testDataAccess = new MemberFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		Member member1 = new Member("Yan Martel", "F42563743156", 150);
		Member member2 = new Member("Suraj Sharma", "X437F356", 250);
		Member member3 = new Member("Ang Lee", "R64565FG4", -1);
		
		testDataAccess.create(member1);
		testDataAccess.create(member2);
		testDataAccess.create(member3);
		
		List<Member> members = testDataAccess.getAll();
		
		assertEquals(3, members.size());
		
	}
	
	@Test
	public void testCreateAndGetAllForThreeMembers_UpdateForTwoMembers() throws UssException {
		testDataAccess = new MemberFileDataAccess(TEST_FILE_NAME, TEST_DATA_DIR);
		
		Member member1 = new Member("Yan Martel", "F42563743156", 150);
		Member member2 = new Member("Suraj Sharma", "X437F356", 250);
		Member member3 = new Member("Ang Lee", "R64565FG4", -1);
		
		testDataAccess.create(member1);
		testDataAccess.create(member2);
		testDataAccess.create(member3);
		
		List<Member> members = testDataAccess.getAll();
		
		assertEquals(3, members.size());
		
		member1.setLoyaltyPoint(100);//deduct from 150 to 100
		member2.setLoyaltyPoint(400);//increase from 250 to 400
		
		testDataAccess.update(member1);
		testDataAccess.update(member2);
		
		List<Member> membersAft = testDataAccess.getAll();
		
		assertEquals(3, members.size());
		
		Member member1FromFile = membersAft.get(0);
		Member member2FromFile = membersAft.get(1);
		
		assertEquals(member1.getLoyaltyPoint(), member1FromFile.getLoyaltyPoint());
		assertEquals(member2.getLoyaltyPoint(), member2FromFile.getLoyaltyPoint());
		
	}
	
	
	private Path getTestPath() {
		
		return Paths.get(TEST_DATA_DIR + "/" + TEST_FILE_NAME);
	}

}
