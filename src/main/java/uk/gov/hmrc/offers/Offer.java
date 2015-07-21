package uk.gov.hmrc.offers;


import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.List;

public abstract class Offer {

    public abstract BigDecimal apply(ProductCatalogue productCatalogue, List<String> products);

    List<String> getProductsEligibleForOffer(List<String> products, final String productToApplyOfferOn) {
        return ListUtils.select(products, new Predicate<String>() {
            @Override
            public boolean evaluate(String productName) {
                return productName.equalsIgnoreCase(productToApplyOfferOn);
            }
        });
    }

}

