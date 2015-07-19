package uk.gov.hmrc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CheckoutTest {

    private static final Map<String, BigDecimal> PRODUCTS;
    static
    {
        PRODUCTS = new HashMap<String, BigDecimal>();
        PRODUCTS.put("apple", new BigDecimal(0.60));
        PRODUCTS.put("orange", new BigDecimal(0.25));
    }
    private ProductCatalogue productCatalogue = new ProductCatalogue(PRODUCTS);

    private Checkout checkout = new Checkout(productCatalogue);
    @Test
    public void costOfOneApple() {
        String totalCost = checkout.checkout(new String[]{"Apple"});
        assertEquals("£0.60", totalCost);
    }

    @Test
    public void costOfOneOrange() {
        String totalCost = checkout.checkout(new String[]{"Orange"});
        assertEquals("£0.25", totalCost);
    }

    @Test
    public void addProducts() {
        String totalCost = checkout.checkout(new String[]{"Apple", "Apple", "Orange", "Apple"});
        assertEquals("£2.05", totalCost);
    }
}