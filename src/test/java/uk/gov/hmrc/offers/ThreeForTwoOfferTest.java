package uk.gov.hmrc.offers;

import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ThreeForTwoOfferTest {

    private ThreeForTwoOffer offer = new ThreeForTwoOffer("Orange");

    @Test
    public void noOfferForOneProduct() {
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Orange"});

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange, price);
    }

    @Test
    public void noOfferForTwoProducts() {
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Orange", "Orange"});

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.add(priceForOneOrange), price);
    }

    @Test
    public void applyOfferForProductsInMultiplesOfThree() {
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Orange", "Orange", "Orange", "Orange", "Orange", "Orange"});

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.multiply(new BigDecimal(4)), price);
    }

    @Test
    public void applyOfferForFourProducts() {
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Orange", "Orange", "Orange", "Orange"});

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.multiply(new BigDecimal(3)), price);
    }

    @Test
    public void applyOfferForFiveProducts() {
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, new String[]{"Orange", "Orange", "Orange", "Orange", "Orange"});

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.multiply(new BigDecimal(4)), price);
    }

}