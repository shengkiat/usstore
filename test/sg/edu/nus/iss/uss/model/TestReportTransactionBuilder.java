package sg.edu.nus.iss.uss.model;

public class TestReportTransactionBuilder {
	
	private TestProductBuilder productBuilder = new TestProductBuilder();
	private TestTransactionBuilder transactionBuilder = new TestTransactionBuilder();
	
	public ReportTransaction build() {
		Transaction transaction = transactionBuilder.build();
		Product product = productBuilder.build();
		return new ReportTransaction(transaction, product);
	}
}
