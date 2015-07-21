package uk.gov.hmrc.offers;


import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface Offer {

    String getProductToApplyOfferOn();

    BigDecimal apply(ProductCatalogue productCatalogue, List<String> products);
}

