package uk.gov.hmrc.offers;


import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;

public interface Offer {

    String getProduct();

    BigDecimal apply(ProductCatalogue productCatalogue, String[] products);
}

