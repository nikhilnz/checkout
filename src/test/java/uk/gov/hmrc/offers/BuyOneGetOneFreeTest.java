package uk.gov.hmrc.offers;

import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BuyOneGetOneFreeTest {

    private BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree("Apple");

    @Test
    public void noOfferOnOneProduct() {
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Apple"});

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple, price);
    }

    @Test
    public void offerOnEvenNumberOfProducts() {
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Apple", "Apple", "Apple", "Apple"});

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)), price);
    }

    @Test
    public void offerOnOddNumberOfProducts() {
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Apple", "Apple", "Apple", "Apple", "Apple"});

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)).add(priceOfOneApple), price);
    }
}