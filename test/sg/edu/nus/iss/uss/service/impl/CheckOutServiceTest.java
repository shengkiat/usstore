package sg.edu.nus.iss.uss.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import sg.edu.nus.iss.uss.dao.IDiscountDataAccess;
import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.dao.ITransactionDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.DiscountFileDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.ProductFileDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.Product;

import sg.edu.nus.iss.uss.service.IMemberService;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.TestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CheckOutServiceTest {

    private CheckOutService checkOutService;

    private IMemberService memberService;
    private IProductService productService;
    private ITransactionService transactionService;
    private MockDiscountService mockDiscountService;
    private MockProductService mockProductService;
    private MockTransactionService mockTransactionService;


    @Before
    public void setUp() throws UssException, IOException {

        IDiscountDataAccess discountDataAccess = new DiscountFileDataAccess();
        mockDiscountService = new MockDiscountService(discountDataAccess);

        IProductDataAccess productDataAccess = new ProductFileDataAccess();
        mockProductService = new MockProductService(productDataAccess);

        ITransactionDataAccess transactionDataAccess = new TransactionFileDataAccess();
        mockTransactionService = new MockTransactionService(transactionDataAccess);

        memberService = TestUtil.setUpMemberServiceWithThreeMember();
        productService = mockProductService;
        transactionService = mockTransactionService;
    }
    
    @After
	public void tearDown() throws Exception {
		TestUtil.destoryMemberServiceAndFile(memberService);
	}
	

    @Test
    public void testDetermineMemberID() {
        createCheckOutSummaryData();
        assertTrue(checkOutService.determineMemberID("F42563743156"));
        assertTrue(checkOutService.determineMemberID("X437F356"));
        assertFalse(checkOutService.determineMemberID("ZZZZZZ"));
        assertFalse(checkOutService.determineMemberID("AAAAAA"));

    }


    @Test
    public void testAddItemIntoCheckOutList() {

        checkOutService = new CheckOutService(memberService, null, null);

        Product product1 = new Product("CLO/1", "Centenary Jumper","A really nice momento",315,21.45,"1234",10,100);
        Product product2 = new Product("STA/1", "NUS Pen","A really cute blue pen",768,5.75,"123459876",50,250);
        Product product3 = new Product("MUG/1", "Centenary Mug","A really nice mug this time",525,10.25,"9876",25,150);
        Product product4 = new Product("STA/2", "NUS Notepad" ,"Great notepad for those lectures",1000,3.15,"6789",25,75);

        List<Product> productItems = new ArrayList<>();
        assertEquals(productItems.size(), 0);

        productItems = checkOutService.addItemIntoCheckOutList(product1);
        assertEquals(productItems.size(), 1);

        productItems = checkOutService.addItemIntoCheckOutList(product2);
        assertEquals(productItems.size(), 2);

        productItems = checkOutService.addItemIntoCheckOutList(product3);
        assertEquals(productItems.size(), 3);

        productItems = checkOutService.addItemIntoCheckOutList(product4);
        assertEquals(productItems.size(), 4);
    }

    @Test
    public void testCalculatePayAmountNormalDiscount() {
        createCheckOutSummaryData();
        int discountAmount = mockDiscountService.findHighestDiscountByMemberID("S1234567A");
        double payAmount = checkOutService.calculatePayAmount(discountAmount);
        assertEquals(payAmount, 36.54, 0);
    }

    @Test
    public void testCalculatePayAmountDiscountZeroPercent() {
        createCheckOutSummaryData();
        int discountAmount = mockDiscountService.findHighestDiscountByMemberID("S1111111B");
        double payAmount = checkOutService.calculatePayAmount(discountAmount);
        assertEquals(payAmount, 40.60, 0);
    }

    @Test
    public void testCalculatePayAmountDiscountRoundDown() {
        createCheckOutSummaryData();
        int discountAmount = 11;
        double payAmount = checkOutService.calculatePayAmount(discountAmount);
        assertEquals(payAmount, 36.13, 0);
    }

    @Test
    public void testCalculatePayAmountDiscountRoundUp() {
        createCheckOutSummaryData();
        int discountAmount = 12;
        double payAmount = checkOutService.calculatePayAmount(discountAmount);
        assertEquals(payAmount, 35.73, 0);
    }

    @Test
    public void testConvertDollarToPointNormalCase() {
        createCheckOutSummaryData();
        double payAmount = checkOutService.calculatePayAmount(0); // no discount given
        int pointsConverted = checkOutService.convertDollarToPoint(payAmount);
        assertEquals(pointsConverted, 4);
    }


    @Test
    public void testCalculateTotalPayableAfterPointsRedemptionWithoutAnyPointsRedemption(){
        createCheckOutSummaryData();
        int discountAmount = 10;
        double payAmount = checkOutService.calculatePayAmount(discountAmount);
        int pointsToRedeem = 0;
        double totalPayable = checkOutService.calculateTotalPayable(payAmount, pointsToRedeem);

        assertEquals(totalPayable, 36.54, 0);
    }

    @Test
    public void testCalculateTotalPayableAfterPointsRedemption100Points(){
        createCheckOutSummaryData();
        int discountAmount = 10;
        double payAmount = checkOutService.calculatePayAmount(discountAmount);
        int pointsToRedeem = 100;
        double totalPayable = checkOutService.calculateTotalPayable(payAmount, pointsToRedeem);

        assertEquals(totalPayable, 31.54, 0);
    }

    @Test
    public void testCalculateTotalPayableAfterPointsRedemption105Points(){ // this will only use 100 points
        createCheckOutSummaryData();
        int discountAmount = 10;
        double totalPayable = checkOutService.calculatePayAmount(discountAmount);
        int pointsToRedeem = 100;
        double amountPaid = checkOutService.calculateTotalPayable(totalPayable, pointsToRedeem);

        assertEquals(amountPaid, 31.54, 0);
    }

    @Test
    public void testMemberMakePaymentDeduct100NoChange() throws UssException {
        createCheckOutSummaryData();
        String memberID = "F42563743156";
        checkOutService.determineMemberID(memberID);

        double payAmount = checkOutService.calculatePayAmount(mockDiscountService.findHighestDiscountByMemberID(memberID));

        double totalPayable = checkOutService.calculateTotalPayable(payAmount, 100);
        assertEquals(31.54, totalPayable, 0);
        double amountPaid = 31.54;
        assertEquals(31.54, amountPaid, 0);
        double changeReceived = checkOutService.memberMakePayment(amountPaid, 100);

        Member member = memberService.getMemberByMemberID("F42563743156");

        /*  original points: 150
            deduct 100 points: 150
            add back 3 points for $31.54 purchase: 53
         */
        assertEquals(53, member.getLoyaltyPoint());
        assertEquals(0, changeReceived, 0);
    }

    @Test
    public void testMemberMakePaymentDeduct100With19Point46DollarsChange() throws UssException {
        createCheckOutSummaryData();
        String memberID = "F42563743156";
        checkOutService.determineMemberID(memberID);

        double payAmount = checkOutService.calculatePayAmount(mockDiscountService.findHighestDiscountByMemberID(memberID));

        double totalPayable = checkOutService.calculateTotalPayable(payAmount, 100);
        assertEquals(31.54, totalPayable, 0);
        double amountPaid = 50.0;
        double changeReceived = checkOutService.memberMakePayment(amountPaid, 100);

        Member member = memberService.getMemberByMemberID("F42563743156");

        /*  original points: 150
            deduct 100 points: 150
            add back 3 points for $31.54 purchase: 53
         */
        assertEquals(53, member.getLoyaltyPoint());
        assertEquals(18.46, changeReceived, 0);
    }

    @Test(expected=UssException.class)
    public void testMemberMakePaymentDeduct100WithLessMoneyReceived() throws UssException {
        createCheckOutSummaryData();
        String memberID = "F42563743156";
        checkOutService.determineMemberID(memberID);

        double payAmount = checkOutService.calculatePayAmount(mockDiscountService.findHighestDiscountByMemberID(memberID));

        double totalPayable = checkOutService.calculateTotalPayable(payAmount, 100);
        assertEquals(31.54, totalPayable, 0);
        double amountPaid = 10.0;

        // exception thrown at here for Amount Received Less Than Amount Payable
        double changeReceived = checkOutService.memberMakePayment(amountPaid, 100);
        assertNull(changeReceived);
    }


    @Test
    public void testNonMemberMakePaymentNoChange() throws UssException {
        createCheckOutSummaryData();
        String memberID = "PUBLICBUYER";
        checkOutService.determineMemberID(memberID);

        double payAmount = checkOutService.calculatePayAmount(0);
        double totalPayable = checkOutService.calculateTotalPayable(payAmount, 0);
        assertEquals(40.60, totalPayable, 0);

        double changeReceived = checkOutService.nonMemberMakePayment(40.60);
        assertEquals(0, changeReceived, 0);
    }

    @Test
    public void testNonMemberMakePaymentDeduct100With19Point46DollarsChange() throws UssException {
        createCheckOutSummaryData();

        double payAmount = checkOutService.calculatePayAmount(0);
        double totalPayable = checkOutService.calculateTotalPayable(payAmount, 0);
        assertEquals(40.60, totalPayable, 0);
        double amountPaid = 50.0;
        double changeReceived = checkOutService.nonMemberMakePayment(amountPaid);

        assertEquals(9.40, changeReceived, 0);
    }

    @Test(expected=UssException.class)
    public void testNonMemberMakePaymentDeduct100WithLessMoneyReceived() throws UssException {
        createCheckOutSummaryData();

        double payAmount = checkOutService.calculatePayAmount(0);
        double totalPayable = checkOutService.calculateTotalPayable(payAmount, 0);
        assertEquals(40.60, totalPayable, 0);
        double amountPaid = 10.0;
        double changeReceived = checkOutService.nonMemberMakePayment(amountPaid);
        assertNull(changeReceived);
    }


    @Test
    public void testAlertIfInventoryLevelBelowThresholdForOneItem() {
        createCheckOutSummaryData();
        List<Product> productsBelowThreshold = checkOutService.alertIfInventoryLevelBelowThreshold(checkOutService.getCheckoutSummary().getCheckoutItems());

        assertEquals(1, productsBelowThreshold.size());
    }

    @Test
    public void testAlertIfInventoryLevelBelowThresholdAllWithin() {
        createCheckOutSummaryData();
        checkOutService.getCheckoutSummary().getCheckoutItems().remove(1); // remove product STA/1
        List<Product> productsBelowThreshold = checkOutService.alertIfInventoryLevelBelowThreshold(checkOutService.getCheckoutSummary().getCheckoutItems());

        assertEquals(0, productsBelowThreshold.size());
    }

    @Test
    public void testPrintoutReceipt(){

    }


    public class MockProductService extends ProductService {

        public MockProductService(IProductDataAccess PrdDataAccess) {
        	super(PrdDataAccess);
        }
        public boolean checkIfProductIsBelowThreshold (Product product) {
            return product.getProductID().equals("STA/1");
        }

        public void deductInventoryFromCheckout(List<Product> productItems) {

        }
    }

    public class MockTransactionService extends TransactionService {
        public MockTransactionService (ITransactionDataAccess transactionDataAccess) {
            super(transactionDataAccess);
        }

        public void createTransactions(List<Product> products, String memberID, Date transactionDate) throws UssException {

        }
    }

    public class MockDiscountService extends DiscountService {
        public MockDiscountService (IDiscountDataAccess discountFileAccess) {
            super(discountFileAccess);
        }

        public int findHighestDiscountByMemberID(String memberID){
            switch(memberID) {
                case("F42563743156"): return  10;
                case("S1111111B"): return 0;
                case("S2222222C"): return 200;
                case("S1234567A"): return 10;
                default: return  30;
            }
        }
    }

    public void createCheckOutSummaryData() {
        checkOutService = new CheckOutService(memberService, transactionService, productService);


        Product product1 = new Product("CLO/1", "Centenary Jumper","A really nice momento",315,21.45,"1234",10,100);
        Product product2 = new Product("STA/1", "NUS Pen","A really cute blue pen",768,5.75,"123459876",50,250);
        Product product3 = new Product("MUG/1", "Centenary Mug","A really nice mug this time",525,10.25,"9876",25,150);
        Product product4 = new Product("STA/2", "NUS Notepad" ,"Great notepad for those lectures",1000,3.15,"6789",25,75);

        checkOutService.addItemIntoCheckOutList(product1);
        checkOutService.addItemIntoCheckOutList(product2);
        checkOutService.addItemIntoCheckOutList(product3);
        checkOutService.addItemIntoCheckOutList(product4);
    }
}
