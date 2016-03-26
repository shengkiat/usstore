package sg.edu.nus.iss.uss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import sg.edu.nus.iss.uss.model.Category;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.model.ReportTransaction;
import sg.edu.nus.iss.uss.model.Transaction;
import sg.edu.nus.iss.uss.service.ICategoryService;
import sg.edu.nus.iss.uss.service.IMemberService;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.IReportingService;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.UssCommonUtil;
import sg.edu.nus.iss.uss.exception.UssException;

public class ReportingService extends UssCommonService implements IReportingService {
	
	private ITransactionService transactionService;
    private IProductService productservice;
	private IMemberService memberService;
	private ICategoryService categoryService;
	
	public ReportingService(ITransactionService transactionService, IProductService productService, IMemberService memberService, ICategoryService categoryService) {
		this.transactionService = transactionService;
		this.productservice = productService;
		this.memberService = memberService;
		this.categoryService = categoryService;
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
	public List<ReportTransaction> retrieveReportTransactions(Date startDate, Date endDate) throws UssException{
		Objects.requireNonNull(startDate, "startDate cannot be null");
		Objects.requireNonNull(endDate, "endDate cannot be null");
		
		if (UssCommonUtil.isDateLeftGreaterThanRight(startDate, endDate)) {
			throw new IllegalArgumentException("startDate cannot be greater than endDate");
		}
		
		List<ReportTransaction> result = new ArrayList<>();
		
		List<Transaction> transactions = transactionService.retrieveTransactionListByDate(startDate, endDate);
		
		for(Transaction transaction : transactions) {
		    
			Product product = productservice.getProductByProductID(transaction.getProductID());	
			
			Objects.requireNonNull(product, "product should not be null using " + transaction.getProductID());
			result.add(new ReportTransaction(transaction, product));
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	@Override
	public String printMembersReport(){
		
		return null;
	}

	@Override
	public List<Member> retrieveMembers() throws UssException {
		List<Member> result = memberService.retrieveMemberList();
		return result;
	}

	@Override
	public List<Category> retrieveCategories() throws UssException {
		List<Category> result = categoryService.retrieveCategoryList();
		return result;
	}
	
}
