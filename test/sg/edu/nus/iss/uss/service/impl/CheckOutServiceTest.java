package sg.edu.nus.iss.uss.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.uss.dao.IDiscountDataAccess;
import sg.edu.nus.iss.uss.dao.IProductDataAccess;
import sg.edu.nus.iss.uss.dao.ITransactionDataAccess;
import sg.edu.nus.iss.uss.dao.filedataaccess.DiscountFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Member;
import sg.edu.nus.iss.uss.model.Product;
import sg.edu.nus.iss.uss.service.IMemberService;
import sg.edu.nus.iss.uss.service.IProductService;
import sg.edu.nus.iss.uss.service.ITransactionService;
import sg.edu.nus.iss.uss.util.TestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yeojc on 15/3/2016.
 */
public class CheckOutServiceTest {

    private CheckOutService checkOutService;

    private IMemberService memberService;
    private IProductService productService;
    private ITransactionService transactionService;
    private MockDiscountService mockDiscountService;
    private MockProductService mockProductService;


    @Before
    public void setUp() throws UssException, IOException {

        IDiscountDataAccess discountDataAccess = new DiscountFileDataAccess();
        mockDiscountService = new MockDiscountService(discountDataAccess);

        IProductDataAccess productDataAccess = null;
        mockProductService = new MockProductService(productDataAccess);

        memberService = (MemberService) TestUtil.setUpMemberServiceWithThreeMember();
        productService = null;
        transactionService = null;
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

        List<Product> productItems = new ArrayList<Product>();
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
    public void testCalculateChargePriceNormalDiscount() {
        createCheckOutSummaryData();
        int discountAmount = mockDiscountService.findHighestDiscountByMemberID("S1234567A");
        double chargePrice = checkOutService.calculateChargePrice(discountAmount);
        assertEquals(chargePrice, 36.54, 0);
    }

    @Test
    public void testCalculateChargePriceDiscountZeroPercent() {
        createCheckOutSummaryData();
        int discountAmount = mockDiscountService.findHighestDiscountByMemberID("S1111111B");
        double chargePrice = checkOutService.calculateChargePrice(discountAmount);
        assertEquals(chargePrice, 40.60, 0);
    }

    @Test
    public void testCalculateChargePriceDiscountRoundDown() {
        createCheckOutSummaryData();
        int discountAmount = 11;
        double chargePrice = checkOutService.calculateChargePrice(discountAmount);
        assertEquals(chargePrice, 36.13, 0);
    }

    @Test
    public void testCalculateChargePriceDiscountRoundUp() {
        createCheckOutSummaryData();
        int discountAmount = 12;
        double chargePrice = checkOutService.calculateChargePrice(discountAmount);
        assertEquals(chargePrice, 35.73, 0);
    }

    @Test
    public void testConvertDollarToPointNormalCase() {
        createCheckOutSummaryData();
        double chargePrice = checkOutService.calculateChargePrice(0); // no discount given
        int pointsConverted = checkOutService.convertDollarToPoint(chargePrice);
        assertEquals(pointsConverted, 4);
    }


    @Test
    public void testCalculateTotalPayableAfterPointsRedemptionWithoutAnyPointsRedemption(){
        createCheckOutSummaryData();
        int discountAmount = 10;
        double payAmount = checkOutService.calculateChargePrice(discountAmount);
        int pointsToRedeem = 0;
        double totalPayable = checkOutService.calculateTotalPayableAfterPointsRedemption(payAmount, pointsToRedeem);

        assertEquals(totalPayable, 36.54, 0);
    }

    @Test
    public void testCalculateTotalPayableAfterPointsRedemption100Points(){
        createCheckOutSummaryData();
        int discountAmount = 10;
        double payAmount = checkOutService.calculateChargePrice(discountAmount);
        int pointsToRedeem = 100;
        double totalPayable = checkOutService.calculateTotalPayableAfterPointsRedemption(payAmount, pointsToRedeem);

        assertEquals(totalPayable, 31.54, 0);
    }

    @Test
    public void testCalculateTotalPayableAfterPointsRedemption105Points(){ // this will only use 100 points
        createCheckOutSummaryData();
        int discountAmount = 10;
        double totalPayable = checkOutService.calculateChargePrice(discountAmount);
        int pointsToRedeem = 100;
        double amountPaid = checkOutService.calculateTotalPayableAfterPointsRedemption(totalPayable, pointsToRedeem);

        assertEquals(amountPaid, 31.54, 0);
    }

    @Test(expected=UssException.class)
    public void testMakePaymentWithFailedPaymentValidation() throws UssException {
        // amount paid and amount redeemed not correct
        createCheckOutSummaryData();
        checkOutService.determineMemberID("F42563743156");
        checkOutService.calculateChargePrice(10);
        checkOutService.makePayment(100.0, 100);
    }

    @Test(expected=RuntimeException.class)
    public void testMakePaymentDeduct100() throws UssException {
        createCheckOutSummaryData();
        checkOutService.determineMemberID("F42563743156");
        checkOutService.calculateChargePrice(10);
        checkOutService.makePayment(31.54, 100);

        Member member = memberService.getMemberByMemberID("F42563743156");

        /*  original points: 150
            deduct 100 points: 150
            add back 3 points for $31.54 purchase: 53
         */
        assertEquals(member.getLoyaltyPoint(), 53);
    }



    @Test
    public void testAlertIfInventoryLevelBelowThresholdForOneItem() {
        createCheckOutSummaryData();
        checkOutService.productService = mockProductService;
        List<Product> productsBelowThreshold = checkOutService.alertIfInventoryLevelBelowThreshold(checkOutService.getCheckoutSummary().getCheckoutItems());

        assertEquals(1, productsBelowThreshold.size());
    }

    @Test
    public void testAlertIfInventoryLevelBelowThresholdAllWithin() {
        createCheckOutSummaryData();
        checkOutService.productService = mockProductService;
        checkOutService.getCheckoutSummary().getCheckoutItems().remove(1); // remove product STA/1
        List<Product> productsBelowThreshold = checkOutService.alertIfInventoryLevelBelowThreshold(checkOutService.getCheckoutSummary().getCheckoutItems());

        assertEquals(0, productsBelowThreshold.size());
    }



    @Test
    public void testPrintoutReceipt(){

    }


    public class MockProductService extends ProductService {
        public MockProductService(IProductDataAccess prdDataAccess) {
        	super(prdDataAccess);
        }
        public boolean checkIfProductIsBelowThreshold (Product product) {
            if(product.getProductID().equals("STA/1")) {
                return true;
            }
            else {
                return false;
            }
        }

        public void deductInventoryFromCheckout(List<Product> productItems) {

        }
    }

    public class MockTransactionService extends TransactionService {
        public MockTransactionService (ITransactionDataAccess transactionDataAccess) {
            super(transactionDataAccess);
        }
    }

    public class MockDiscountService extends DiscountService {
        public MockDiscountService (IDiscountDataAccess discountFileAccess) {
            super(discountFileAccess);
        }

        public int findHighestDiscountByMemberID(String memberID){
            if(memberID.equals("S1234567A")) {
                return 10;
            }
            else if (memberID.equals("S1111111B")) {
                return 0;
            }
            else if (memberID.equals("S2222222C")) {
                return 200;
            }
            else {
                return 30;
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
