package sg.edu.nus.iss.uss.service;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.ReportTransaction;

public interface IReportingService {
	
	public String printCategoriesReport();
	
	public String printProductsReport();
	
	public List<ReportTransaction> retrieveReportTransactions(Date startDate, Date endDate) throws UssException;
	
	public String printMembersReport();
	
}
