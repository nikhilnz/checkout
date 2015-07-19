package uk.gov.hmrc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductCatalogueTest {

    @Test
    public void getPrice() {
        BigDecimal price = ProductCatalogue.DEFAULT_CATALOGUE.getPrice("apple");

        assertEquals(new BigDecimal(0.60).setScale(2, BigDecimal.ROUND_UP), price);
    }

}