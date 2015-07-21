package uk.gov.hmrc.offers;


import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BuyOneGetOneFreeTest {

    private BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree("Apple");

    @Test
    public void noOfferOnOneProduct() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple, price);
    }

    @Test
    public void offerOnEvenNumberOfProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)), price);
    }

    @Test
    public void offerOnOddNumberOfProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        products.add("Apple");
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)).add(priceOfOneApple), price);
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
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)), price);

    }

    @Test
    public void modifyTheProductListToRemoveTheProductsThatHaveTheOfferApplied() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Orange");
        products.add("Apple");
        products.add("Orange");
        products.add("Apple");
        products.add("Apple");
        buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals( 2, products.size());
        assertEquals(Arrays.asList("Orange", "Orange"), products);

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