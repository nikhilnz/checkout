package uk.gov.hmrc.offers;


import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BuyOneGetOneFreeTest {

    private BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree("Apple");
    public static final BigDecimal PRICE_OF_ONE_APPLE = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");

    @Test
    public void noOfferOnOneProduct() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        BigDecimal discount = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(new BigDecimal(0.00), discount);
    }

    @Test
    public void offerOnEvenNumberOfProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        BigDecimal discount = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_OF_ONE_APPLE.add(PRICE_OF_ONE_APPLE), discount);
    }

    @Test
    public void offerOnOddNumberOfProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        BigDecimal discount = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_OF_ONE_APPLE.add(PRICE_OF_ONE_APPLE), discount);
    }

    @Test
    public void onlyApplyOfferOnProductEligible() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Orange");
        products.add("Apple");
        products.add("Orange");
        products.add("Apple");
        products.add("Apple");
        BigDecimal discount = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_OF_ONE_APPLE.add(PRICE_OF_ONE_APPLE), discount);
    }

    @Test
    public void noOfferIfNoEligibleProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(new BigDecimal(0.00), price);
    }
}