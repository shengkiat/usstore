package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.MemberDataAccess;
import sg.edu.nus.iss.uss.model.Member;

public class MemberFileDataAccess extends FileDataAccess implements MemberDataAccess {

	public MemberFileDataAccess() {
		super("Members.dat");
	}

	@Override
	public List<Member> getAll() {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void create(Member e) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void update(Member e) {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected void initialLoad() {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		throw new RuntimeException("not implemented yet");
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return 0;
	}
}
