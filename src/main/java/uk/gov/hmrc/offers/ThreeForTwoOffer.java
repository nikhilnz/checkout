package uk.gov.hmrc.offers;


import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;

public class ThreeForTwoOffer implements Offer {
    private String product;

    public ThreeForTwoOffer(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public BigDecimal apply(ProductCatalogue productCatalogue, String[] products) {
        BigDecimal priceOfOneUnit = productCatalogue.getPrice(product.toLowerCase());
        if (products.length < 3) {
           return priceOfOneUnit.multiply(new BigDecimal(products.length));
        } else if(products.length % 3 == 0) {
            return priceOfOneUnit.multiply(new BigDecimal((products.length / 3) * 2));
        } else {
            return priceOfOneUnit.multiply(new BigDecimal((products.length / 3) * 2)).add(priceOfOneUnit.multiply(new BigDecimal(products.length % 3)));
        }
    }
}
