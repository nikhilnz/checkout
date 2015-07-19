package uk.gov.hmrc;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutTest {

    @Test
    public void costOfOneApple() {
        Checkout checkout = new Checkout();

        String[] products = new String[] {"Apple"};
        String totalCost = checkout.checkout(products);

        assertEquals("0.6", totalCost);
    }

    @Test
    public void costOfOneOrange() {
        Checkout checkout = new Checkout();

        String[] products = new String[] {"Orange"};
        String totalCost = checkout.checkout(products);

        assertEquals("0.25", totalCost);
    }

    @Test
    public void addProducts() {
        Checkout checkout = new Checkout();
        String[] products = new String[] {"Apple", "Apple", "Orange", "Apple"};
        String totalCost = checkout.checkout(products);

        assertEquals("2.05", totalCost);
    }
}