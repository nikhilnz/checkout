package uk.gov.hmrc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductCatalogue {

    private Map<String, BigDecimal> catalogue;

    public ProductCatalogue(Map<String, BigDecimal> catalogue) {
        this.catalogue = catalogue;
    }

    private Map<String, BigDecimal> getCatalogue() {
        return catalogue;
    }

    private static final Map<String, BigDecimal> PRODUCTS;
    static
    {
        PRODUCTS = new HashMap<String, BigDecimal>();
        PRODUCTS.put("apple", new BigDecimal(0.60));
        PRODUCTS.put("orange", new BigDecimal(0.25));
    }
    public static ProductCatalogue DEFAULT_CATALOGUE = new ProductCatalogue(PRODUCTS);

    public BigDecimal getPrice(String product) {
        return getCatalogue().get(product.toLowerCase()).setScale(2, BigDecimal.ROUND_UP);
    }
}
