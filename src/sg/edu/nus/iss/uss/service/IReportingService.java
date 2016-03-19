package sg.edu.nus.iss.uss.service;

import java.util.Date;

public interface IReportingService {
	
	public String printCategoriesReport();
	
	public String printProductsReport();
	
	public String printTransactionsReportByDay(Date startDay, Date endDay);
	
	public String printMembersReport();
	
}
