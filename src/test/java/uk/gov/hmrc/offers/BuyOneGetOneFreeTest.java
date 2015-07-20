package uk.gov.hmrc.offers;

import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BuyOneGetOneFreeTest {

    @Test
    public void noOfferOnOneProduct() {
        BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree(null);
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Apple"});

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple, price);
    }

    @Test
    public void offerOnEvenNumberOfProducts() {
        BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree(null);
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Apple", "Apple", "Apple", "Apple"});

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)), price);
    }

    @Test
    public void offerOnOddNumberOfProducts() {
        BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree(null);
        BigDecimal price = buyOneGetOneFree.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Apple", "Apple", "Apple", "Apple", "Apple"});

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");
        assertEquals(priceOfOneApple.multiply(new BigDecimal(2)).add(priceOfOneApple), price);
    }
}