package uk.gov.hmrc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PriceFormatterTest {

    @Test
    public void formatForPenceWithZeroAtTheEnd() {
        PriceFormatter priceFormatter = new PriceFormatter();
        BigDecimal price = new BigDecimal(0.60);
        String formattedPrice = priceFormatter.format(price);

        assertEquals("£0.60", formattedPrice);
    }

    @Test
    public void formatForTwoDigitPence() {
        PriceFormatter priceFormatter = new PriceFormatter();
        BigDecimal price = new BigDecimal(0.25);
        String formattedPrice = priceFormatter.format(price);

        assertEquals("£0.25", formattedPrice);
    }


    @Test
    public void formatForPoundsAndPence() {
        PriceFormatter priceFormatter = new PriceFormatter();
        BigDecimal price = new BigDecimal(2.25);
        String formattedPrice = priceFormatter.format(price);

        assertEquals("£2.25", formattedPrice);
    }
}