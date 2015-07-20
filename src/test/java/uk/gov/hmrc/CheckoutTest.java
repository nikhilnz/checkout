package uk.gov.hmrc;

import org.junit.Test;
import uk.gov.hmrc.offers.BuyOneGetOneFree;
import uk.gov.hmrc.offers.Offer;
import uk.gov.hmrc.offers.ThreeForTwoOffer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static final List<Offer> OFFERS;
    static
    {
        OFFERS = new ArrayList< Offer>();
        OFFERS.add(new BuyOneGetOneFree("apple"));
        OFFERS.add(new ThreeForTwoOffer("orange"));
    }
    private OfferCatalogue  offerCatalogue = new OfferCatalogue(OFFERS);

    private Checkout checkoutWithoutOffer = new Checkout(productCatalogue, null);
    private Checkout checkoutWithOffer = new Checkout(productCatalogue, offerCatalogue);

    @Test
    public void costOfOneAppleWithoutOffer() {
        String totalCost = checkoutWithoutOffer.checkout(new String[]{"Apple"});
        assertEquals("£0.60", totalCost);
    }

    @Test
    public void costOfOneOrangeWithoutOffer() {
        String totalCost = checkoutWithoutOffer.checkout(new String[]{"Orange"});
        assertEquals("£0.25", totalCost);
    }

    @Test
    public void addProductsWithoutOffer() {
        String totalCost = checkoutWithoutOffer.checkout(new String[]{"Apple", "Apple", "Orange", "Apple"});
        assertEquals("£2.05", totalCost);
    }

    @Test
    public void costOfOneAppleWithOffer() {
        String totalCost = checkoutWithOffer.checkout(new String[]{"Apple"});
        assertEquals("£0.60", totalCost);
    }

    @Test
    public void costOfTwoApplesWithOffer() {
        String totalCost = checkoutWithOffer.checkout(new String[]{"Apple", "Apple"});
        assertEquals("£0.60", totalCost);
    }

    @Test
    public void costOfOneOrangeWithOffer() {
        String totalCost = checkoutWithOffer.checkout(new String[]{"Orange"});
        assertEquals("£0.25", totalCost);
    }

    @Test
    public void costOf3OrangesWithOffer() {
        String totalCost = checkoutWithOffer.checkout(new String[]{"Orange", "Orange", "Orange"});
        assertEquals("£0.50", totalCost);
    }



}