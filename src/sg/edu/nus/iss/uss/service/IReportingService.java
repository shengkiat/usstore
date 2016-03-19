package sg.edu.nus.iss.uss.service;

import java.util.Date;

public interface IReportingService {
	
	public String printCategoriesReport();
	
	public String printProductsReport();
	
	public String printTransactionsReportByDay(Date startDate, Date endDate);
	
	public String printMembersReport();
	
}
