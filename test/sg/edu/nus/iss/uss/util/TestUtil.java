package sg.edu.nus.iss.uss.util;

import java.io.File;

public class TestUtil {
	
	private static final String TEST_DATA_DIR;
	
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

}
