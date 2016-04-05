package sg.edu.nus.iss.uss.model;

public class ReceiptPrinter implements IReceiptPrinter {
	
	private IPrinter printer;
	
	public ReceiptPrinter(IPrinter printer) {
		this.printer = printer;
	}

	@Override
	public void printMemberReceipt(PurchasedInformation purchasedInformation) {
		
		for(String item : purchasedInformation.getItems()) {
			printer.print(item);
		}
		
		printer.print("Total $" + purchasedInformation.getSubTotal());
		printer.print("Loyalty Points Deducted in Dollars $" + purchasedInformation.getDollarToRedeem());
        printer.print("Loyalty Points Awarded in this Transaction " + purchasedInformation.getPointsAdded());
        printer.print("Loyalty Points Remaining in Dollars $" + purchasedInformation.getLoyaltyPointRemained());
        printer.print("Amount Received $" + purchasedInformation.getAmountReceived());
        printer.print("Change $" + purchasedInformation.getChange());
	}

	@Override
	public void printNonMemberReceipt(PurchasedInformation purchasedInformation) {
		for(String item : purchasedInformation.getItems()) {
			printer.print(item);
		}
		
		printer.print("Total $" + purchasedInformation.getSubTotal());
		printer.print("Amount Received $" + purchasedInformation.getAmountReceived());
		printer.print("Change $" + purchasedInformation.getChange());
	}

}
