package uk.gov.hmrc.offers;

import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ThreeForTwoOfferTest {

    public static final BigDecimal ZERO_PRICE = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_UP);
    private ThreeForTwoOffer offer = new ThreeForTwoOffer("Orange");
    public static final BigDecimal PRICE_FOR_ONE_ORANGE = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");

    @Test
    public void noOfferForOneProduct() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        BigDecimal discount = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(ZERO_PRICE, discount);
    }

    @Test
    public void noOfferForTwoProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        BigDecimal discount = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(ZERO_PRICE, discount);
    }

    @Test
    public void applyOfferForProductsInMultiplesOfThree() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        BigDecimal discount = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_FOR_ONE_ORANGE.add(PRICE_FOR_ONE_ORANGE), discount);
    }

    @Test
    public void applyOfferForFourProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        BigDecimal discount = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_FOR_ONE_ORANGE, discount);
    }

    @Test
    public void applyOfferForFiveProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        BigDecimal discount = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_FOR_ONE_ORANGE, discount);
    }


    @Test
    public void onlyApplyOfferOnProductsEligible() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Orange");
        products.add("Apple");
        products.add("Orange");
        products.add("Apple");
        products.add("Apple");
        products.add("Orange");
        BigDecimal discount = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(PRICE_FOR_ONE_ORANGE, discount);

    }

    @Test
    public void noOfferIfNoEligibleProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Apple");
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(ZERO_PRICE, price);
    }

}