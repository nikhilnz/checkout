package uk.gov.hmrc;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private PriceFormatter priceFormatter = new PriceFormatter();

    private static final Map<String, BigDecimal> PRODUCTS;
    static
    {
        PRODUCTS = new HashMap<String, BigDecimal>();
        PRODUCTS.put("apple", new BigDecimal(0.60));
        PRODUCTS.put("orange", new BigDecimal(0.25));
    }

    public String checkout(String[] products) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        for (String product : products) {
            BigDecimal price = PRODUCTS.get(product.toLowerCase());
            totalPrice = totalPrice.add(price);
        }
        return priceFormatter.format(totalPrice);
    }
}
