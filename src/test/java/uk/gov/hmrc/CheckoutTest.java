package uk.gov.hmrc;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutTest {

    @Test
    public void costOfOneApple() {
        Checkout checkout = new Checkout();
        String totalCost = checkout.checkout("Apple");

        assertEquals("0.6", totalCost);
    }

    @Test
    public void costOfOneOrange() {
        Checkout checkout = new Checkout();
        String totalCost = checkout.checkout("Orange");

        assertEquals("0.25", totalCost);
    }

}