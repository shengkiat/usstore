package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.ReportTransaction;

public interface IReportingService {
	
	public List<ReportTransaction> retrieveReportTransactions(Date startDate, Date endDate) throws UssException;
	
	public List<Member> retrieveMembers() throws UssException;
	
	public List<Category> retrieveCategories() throws UssException;
}
