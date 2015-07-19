package uk.gov.hmrc;


import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private static final Map<String, Double> PRODUCTS;
    static
    {
        PRODUCTS = new HashMap<String, Double>();
        PRODUCTS.put("apple", new Double(0.60));
        PRODUCTS.put("orange", new Double(0.25));
    }

    public String checkout(String apple) {
        return PRODUCTS.get(apple.toLowerCase()).toString();
    }
}
