package sg.edu.nus.iss.uss.service;

import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.uss.model.Product;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yeojc on 15/3/2016.
 */
public class CheckOutServiceTest {

    private CheckOutService checkOutService;

    private MockMemberService mockMemberService;
    private MockDiscountService mockDiscountService;


    @Before
    public void setUp() {
        mockMemberService = new MockMemberService();
        mockDiscountService = new MockDiscountService();
    }

    @Test
    public void testDetermineMemberID() {
        // to call member service to determine memberID

        assertTrue(mockMemberService.isValidMember("S1234567A"));
        assertTrue(mockMemberService.isValidMember("S1111111B"));
        assertFalse(mockMemberService.isValidMember("ZZZZZZ"));
        assertFalse(mockMemberService.isValidMember("AAAAAA"));
    }


    @Test
    public void testAddItemIntoCheckOutList() {
        checkOutService = new CheckOutService();
        Product product1 = new Product("CLO/1", "Centenary Jumper","A really nice momento",315,21.45,1234,10,100);
        Product product2 = new Product("STA/1", "NUS Pen","A really cute blue pen",768,5.75,123459876,50,250);
        Product product3 = new Product("MUG/1", "Centenary Mug","A really nice mug this time",525,10.25,9876,25,150);
        Product product4 = new Product("STA/2", "NUS Notepad" ,"Great notepad for those lectures",1000,3.15,6789,25,75);

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
    public void testCalculateChargePrice(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        int discountAmount = mockDiscountService.findHighestDiscountByMemberID("S1234567A");
        createCheckOutSummaryData();
        double totalProductPrice = checkOutService.getCheckoutSummary().getTotalPrice();

        double chargePrice = totalProductPrice * ((100.0 - discountAmount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        assertEquals(chargePrice, 36.54, 0);

        discountAmount = mockDiscountService.findHighestDiscountByMemberID("S1111111B");
        chargePrice = totalProductPrice * ((100.0 - discountAmount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        assertEquals(chargePrice, 40.60, 0);

//        // to test more than 100% discount this will be an exception
//        discountAmount = mockDiscountService.findHighestDiscountByMemberID("S2222222C");
//        chargePrice = totalProductPrice * ((100.0 - discountAmount) / 100.0);
//        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        // todo: to throw exception

        // to test round down
        discountAmount = 11;
        chargePrice = totalProductPrice * ((100.0 - discountAmount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        assertEquals(chargePrice, 36.13, 0);

        // to test round up
        discountAmount = 12;
        chargePrice = totalProductPrice * ((100.0 - discountAmount) / 100.0);
        chargePrice = (double) Math.round(chargePrice * 100d) / 100d ;
        assertEquals(chargePrice, 35.73, 0);
    }

    @Test
    public void testMakePayment(){

    }



    @Test
    public void testPaymentValidation(){
    }

    @Test
    public void testAlertIfInventoryLevelBelowThreshold() {

    }



    @Test
    public void testPrintoutReceipt(){

    }


    public class MockMemberService extends MemberService {
        private String member1;
        private String member2;
        private String member3;

        public MockMemberService() {
            member1 = "S1234567A";
            member2 = "S1111111B";
            member3 = "S2222222C";

        }

        public boolean isValidMember(String memberID) {
            if(memberID.equals(member1) || memberID.equals(member2) || memberID.equals(member3)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public class MockProductService extends  ProductService {
        public MockProductService() {

        }

        public boolean checkIfProductIsBelowThreshold (Product product) {
            // todo to return true false to check if product inventory has fallen below threshold

            if(product.getProductID().equals("STA/1")) {
                return false;
            }
            else {
                return true;
            }

        }
    }

    public class MockDiscountService extends DiscountService {
        public MockDiscountService () {

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
        checkOutService = new CheckOutService();
        Product product1 = new Product("CLO/1", "Centenary Jumper","A really nice momento",315,21.45,1234,10,100);
        Product product2 = new Product("STA/1", "NUS Pen","A really cute blue pen",768,5.75,123459876,50,250);
        Product product3 = new Product("MUG/1", "Centenary Mug","A really nice mug this time",525,10.25,9876,25,150);
        Product product4 = new Product("STA/2", "NUS Notepad" ,"Great notepad for those lectures",1000,3.15,6789,25,75);

        checkOutService.addItemIntoCheckOutList(product1);
        checkOutService.addItemIntoCheckOutList(product2);
        checkOutService.addItemIntoCheckOutList(product3);
        checkOutService.addItemIntoCheckOutList(product4);
    }
}
