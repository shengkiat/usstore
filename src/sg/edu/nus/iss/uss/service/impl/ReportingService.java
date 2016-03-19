package sg.edu.nus.iss.uss.service.impl;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.service.IReportingService;

public class ReportingService extends UssCommonService implements IReportingService{
	
	@Override
	public String printCategoriesReport(){
		//TODO
		
		return "";
	}
	
	@Override
	public String printProductsReport(){
		//TODO
		
		return "";
	}
	
	@Override
	public List<ReportTransaction> retrieveReportTransactions(Date startDate, Date endDate){
		//TODO
		
		return null;
	}
	
	@Override
	public String printMembersReport(){
		//TODO
		
		return "";
	}
	
}
