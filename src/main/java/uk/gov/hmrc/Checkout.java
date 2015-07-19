package uk.gov.hmrc;


import java.math.BigDecimal;

public class Checkout {

    private PriceFormatter priceFormatter = new PriceFormatter();
    private ProductCatalogue productCatalogue;

    public Checkout() {
        this(ProductCatalogue.DEFAULT_CATALOGUE);
    }

    public Checkout(ProductCatalogue productCatalogue ) {
        this.productCatalogue = productCatalogue;
    }

    public String checkout(String[] products) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        for (String product : products) {
            BigDecimal price = productCatalogue.getPrice(product.toLowerCase());
            totalPrice = totalPrice.add(price);
        }
        return priceFormatter.format(totalPrice);
    }
}
