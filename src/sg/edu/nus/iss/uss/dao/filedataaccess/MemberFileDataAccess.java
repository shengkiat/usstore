package sg.edu.nus.iss.uss.dao.filedataaccess;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.uss.dao.MemberDataAccess;
import sg.edu.nus.iss.uss.exception.ErrorConstants;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;

public class MemberFileDataAccess extends FileDataAccess implements MemberDataAccess {
	
	private static final int FIELD_NAME = 0;
	private static final int FIELD_MEMBER_ID = 1;
	private static final int FIELD_LOYALTY_POINT = 2;
	
	private static final int TOTAL_FIELDS = 3;
	
	private List<Member> records;

	public MemberFileDataAccess() {
		super("Members.dat");
	}
	
	public MemberFileDataAccess(String fileName, String directory) {
		super(fileName, directory);	
	}
	
	@Override
	protected void initialLoad() {
		getAll();
		
	}
	
	private List<Member> getRecords(){
		return records;
	}
	
	private void setRecords(List<Member> members){
		records = members;
	}
	
	private void addRecord(Member member){
		records.add(member);
	}

	@Override
	public List<Member> getAll() {
		
		List<Member> members = getRecords();
		
		if(members != null){
			return members;//if exist, return the cached member list.
		}
		
		members = new LinkedList<Member>();
		
		List<String[]> stringContent = readAll();
		for(String[] arr : stringContent) {

			members.add(strArrayToMember(arr));
		}
		
		setRecords(members);//initial records
		
		return members;
		
	}
	
	@Override
	public Member getMemberByMemberID(String memberID) {
		
		if(memberID == null || memberID.equals("")){
			return null;
		}
		
		List<Member> members = getAll();
		Member member = null;
		for(Member m : members){
			if(memberID.equalsIgnoreCase(m.getMemberID())){
				member = m;
			}
		}
		
		return member;
	}
	
	@Override
	public void createMembers(List<Member> members) throws UssException{
		for(Member m : members){
			create(m);
		}
	}

	@Override
	public void create(Member member) throws UssException {
		String[] arr = memberToStrArray(member);

		writeNewLine(arr);
		
		addRecord(member);
	}

	@Override
	public void update(Member member) throws UssException {
		
		boolean isExist = false;
		
		if(member != null){
			for(Member m : getAll()){
				if(member.getMemberID().equalsIgnoreCase(m.getMemberID())){
					m = member;
					isExist = true;
				}
			}
		}
		
		if(!isExist){
			throw new UssException(ErrorConstants.UssCode.MEMBER, ErrorConstants.MEMBER_NOT_EXISTS);
		}else{
			overwriteLine(memberToStrArray(member));
		}
		
	}
	
	@Override
	public boolean isMemberExist(String memberID){
		
		for(Member m : getAll()){
			if(memberID.equalsIgnoreCase(m.getMemberID())){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	protected String getPrimaryKey(String[] arr) {
		return arr[1];
	}
	
	@Override
	protected int getTotalNumberOfFields() {
		return TOTAL_FIELDS;
	}
	
	private Member strArrayToMember(String [] arr){
		
		String name = arr[FIELD_NAME];
		String memberID = arr[FIELD_MEMBER_ID];
		int loyaltyPoint = Integer.parseInt(arr[FIELD_LOYALTY_POINT]);
		
		Member m = new Member(name, memberID, loyaltyPoint);
		
		return m;
	}
	
	private String[] memberToStrArray(Member member){
		
		String[] arr = new String[TOTAL_FIELDS];
		arr[FIELD_NAME] = member.getName();
		arr[FIELD_MEMBER_ID] = member.getMemberID();
		arr[FIELD_LOYALTY_POINT] = Integer.toString(member.getLoyaltyPoint());
		
		return arr;
	}

	
}
