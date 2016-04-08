package sg.edu.nus.iss.uss.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReceiptPrinterTest {
	
	private MockPrinter printer;
	private IReceiptPrinter testReceiptPrinter;

	@Test
	public void testPrintMemberReceipt() {
		List<String> items = new ArrayList<>();
		items.add("Centenary Jumper");
		items.add("Centenary Jumper");
		
		PurchasedInformation purchasedInformation = new PurchasedInformation(items, 20.0, 30.0, 10.0, 0, 2, 100);
		testReceiptPrinter.printMemberReceipt(purchasedInformation);
		
		assertEquals("Centenary Jumper", printer.getLines().get(0));
		assertEquals("Centenary Jumper", printer.getLines().get(1));
		assertEquals("Total $20.0", printer.getLines().get(2));
		assertEquals("Loyalty Points Deducted in Dollars $0.0", printer.getLines().get(3));
		assertEquals("Loyalty Points Awarded in this Transaction 2", printer.getLines().get(4));
		assertEquals("Loyalty Points Remaining in Dollars $100", printer.getLines().get(5));
		assertEquals("Amount Received $30.0", printer.getLines().get(6));
		assertEquals("Change $10.0", printer.getLines().get(7));
	}
	
	@Test
	public void testPrintNonMemberReceipt() {
		List<String> items = new ArrayList<>();
		items.add("Centenary Jumper");
		items.add("Centenary Jumper");
		
		PurchasedInformation purchasedInformation = new PurchasedInformation(items, 20.0, 30.0, 10.0);
		testReceiptPrinter.printNonMemberReceipt(purchasedInformation);
		
		assertEquals("Centenary Jumper", printer.getLines().get(0));
		assertEquals("Centenary Jumper", printer.getLines().get(1));
		assertEquals("Total $20.0", printer.getLines().get(2));
		assertEquals("Amount Received $30.0", printer.getLines().get(3));
		assertEquals("Change $10.0", printer.getLines().get(4));
	}
	
	@Before
	public void setUp() {
		printer = new MockPrinter();
		testReceiptPrinter = new ReceiptPrinter(printer);
	}
	
	@After
	public void tearDown() {
		testReceiptPrinter = null;
		printer = null;
	}
	
	private class MockPrinter implements IPrinter {
		
		private List<String> lines = new ArrayList<>();

		@Override
		public void print(String line) {
			lines.add(line);
		}

		public List<String> getLines() {
			return lines;
		}
		
		
		
	}

}
