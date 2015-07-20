package uk.gov.hmrc.offers;


import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;

public class BuyOneGetOneFree implements Offer {
    private String product;

    public BuyOneGetOneFree(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public BigDecimal apply(ProductCatalogue productCatalogue, String[] products) {
        BigDecimal priceOfOneUnit = productCatalogue.getPrice(products[0].toLowerCase());
        if (products.length == 1) {
            return priceOfOneUnit;
        } else if(products.length % 2 == 0) {
            return priceOfOneUnit.multiply(new BigDecimal(products.length / 2));
        } else {
            return priceOfOneUnit.multiply(new BigDecimal(products.length / 2)).add(priceOfOneUnit);
        }
    }
}
