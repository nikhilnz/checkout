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
        return null;
    }
}
