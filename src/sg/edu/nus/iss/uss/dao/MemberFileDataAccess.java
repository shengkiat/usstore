package sg.edu.nus.iss.uss.dao;

import java.util.List;

import sg.edu.nus.iss.uss.model.Member;

public class MemberFileDataAccess extends FileDataAccess implements IDataAccess<Member> {

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

}
