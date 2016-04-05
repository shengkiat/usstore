package sg.edu.nus.iss.uss.model;

public interface IReceiptPrinter {
	
	public void printMemberReceipt(PurchasedInformation purchasedInformation);
	public void printNonMemberReceipt(PurchasedInformation purchasedInformation);
}
