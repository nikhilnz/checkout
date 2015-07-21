package uk.gov.hmrc.offers;

import org.assertj.core.util.Lists;
import org.junit.Test;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ThreeForTwoOfferTest {

    private ThreeForTwoOffer offer = new ThreeForTwoOffer("Orange");

    @Test
    public void noOfferForOneProduct() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange, price);
    }

    @Test
    public void noOfferForTwoProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.add(priceForOneOrange), price);
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
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.multiply(new BigDecimal(4)), price);
    }

    @Test
    public void applyOfferForFourProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.multiply(new BigDecimal(3)), price);
    }

    @Test
    public void applyOfferForFiveProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        products.add("Orange");
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceForOneOrange = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
        assertEquals(priceForOneOrange.multiply(new BigDecimal(4)), price);
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
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        BigDecimal priceOfOneApple = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("orange");
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
        offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(4, products.size());
        assertEquals(Arrays.asList("Apple", "Apple", "Apple", "Apple"), products);

    }

    @Test
    public void noOfferIfNoEligibleProducts() {
        List<String> products = new ArrayList<String>();
        products.add("Apple");
        products.add("Apple");
        BigDecimal price = offer.apply(ProductCatalogue.DEFAULT_CATALOGUE, products);

        assertEquals(new BigDecimal(0.00), price);
    }

}