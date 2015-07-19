package uk.gov.hmrc;


import java.math.BigDecimal;

public class PriceFormatter {
    public String format(BigDecimal price) {
        return "£" + price.setScale(2,BigDecimal.ROUND_UP).toString();
    }
}
