package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.IReportingService;
import sg.edu.nus.iss.uss.service.ITransactionService;

public class ReportingService extends UssCommonService implements IReportingService {
	
	private ITransactionService transactionService;
	private IProductService productService;
	
	public ReportingService(ITransactionService transactionService, IProductService productService) {
		this.transactionService = transactionService;
		this.productService = productService;
	}
	
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
		List<ReportTransaction> result = new ArrayList<>();
		
		
		
		return result;
	}
	
	@Override
	public String printMembersReport(){
		//TODO
		
		return "";
	}
	
}
