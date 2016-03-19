package sg.edu.nus.iss.uss.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import sg.edu.nus.iss.uss.dao.filedataaccess.MemberFileDataAccess;
import sg.edu.nus.iss.uss.service.MemberService;

public class TestUtil {
	
	private static final String TEST_DATA_DIR;
	private static final String TEST_FILE_FOR_MEMBER = "Members.dat";
	
	static {
		StringBuilder builder = new StringBuilder(System.getProperty("user.dir"));
		builder.append(File.separator).append("test");
		builder.append(File.separator).append("sg");
		builder.append(File.separator).append("edu");
		builder.append(File.separator).append("nus");
		builder.append(File.separator).append("iss");
		builder.append(File.separator).append("uss");
		builder.append(File.separator).append("dao");
		builder.append(File.separator).append("filedataaccess");
		TEST_DATA_DIR = builder.toString();
	}
	
	private TestUtil() {
	}
	
	public static String getTestDirectoryForFile() {
		return TEST_DATA_DIR;
	}
	
	public static String getTestPath(String fileName) {
		return TEST_DATA_DIR + File.separator + fileName;
	}

	
	public static MemberService setUpMemberServiceWithThreeMember() throws IOException{
		String fileName = getTestPath(TEST_FILE_FOR_MEMBER);
		initFileWithThreeMembers(fileName);
		MemberService service = new MemberService(new MemberFileDataAccess(TEST_FILE_FOR_MEMBER, TEST_DATA_DIR));
		return service;
	}
	
	public static void destoryMemberServiceAndFile(MemberService memberService){
		
		memberService = null;
		destoryFile(getTestPath(TEST_FILE_FOR_MEMBER));
	}
	
	
	private static void initFileWithThreeMembers(String fileName) throws IOException{
		/*
		 * Create Members.dat file with three members
		 * 
		 */
		
		String [] lines = new String []{
			"Yan Martel,F42563743156,150",
			"Suraj Sharma,X437F356,250",
			"Ang Lee,R64565FG4,-1"
		};

		createFileWithLines(fileName, lines);
		
	}

	public static void createFileWithLines(String fileFullPath, String[] lines) throws IOException {
//		File file = new File("C:\\NUS\\project\\usstore\\test\\sg\\edu\\nus\\iss\\uss\\filedataaccess\\Members.dat");

		File file = new File(fileFullPath);
		
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
	
	public static void destoryFile(String fileFullPath){

		File file = new File(fileFullPath);

		// if file doesnt exists, then create it
		if (file.exists()) {
			file.delete();
		}
	}

}
