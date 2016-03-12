package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.List;

import sg.edu.nus.iss.uss.dao.IDataAccess;
import sg.edu.nus.iss.uss.model.Member;

public class MemberFileDataAccess extends FileDataAccess implements IDataAccess<Member> {

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
}
