package sg.edu.nus.iss.uss.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by yeojc on 16/3/2016.
 */
public class CheckOutSummaryTest {

    private CheckoutSummary checkoutSummary;

    @Before
    public void setUp() {
        checkoutSummary = new CheckoutSummary();

        Product product1 = new Product("CLO/1", "Centenary Jumper","A really nice momento",315,21.45,"1234",10,100);
        Product product2 = new Product("STA/1", "NUS Pen","A really cute blue pen",768,5.75,"123459876",50,250);
        Product product3 = new Product("MUG/1", "Centenary Mug","A really nice mug this time",525,10.25,"9876",25,150);
        Product product4 = new Product("STA/2", "NUS Notepad" ,"Great notepad for those lectures",1000,3.15,"6789",25,75);

        List<Product> productItems = new ArrayList<Product>();

        productItems.add(product1);
        productItems.add(product2);
        productItems.add(product3);
        productItems.add(product4);

        checkoutSummary.setCheckoutItems(productItems);
    }


    @Test
    public void testGetTotalPrice() {
        // total price of 4 items to be 40.60
        assertNotEquals(checkoutSummary.getTotalPrice(),0.0, 0);
        assertEquals(checkoutSummary.getTotalPrice(), 40.60, 0);
        assertNotEquals(checkoutSummary.getTotalPrice(),1000.00, 0);
    }


}

