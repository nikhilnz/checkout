package uk.gov.hmrc.offers;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BuyOneGetOneFree extends Offer {
    private String productToApplyOfferOn;

    public BuyOneGetOneFree(String productToApplyOfferOn) {
        this.productToApplyOfferOn = productToApplyOfferOn;
    }

    @Override
    public BigDecimal apply(ProductCatalogue productCatalogue, List<String> products) {
        List<String> productsEligibleForOffer = getProductsEligibleForOffer(products, productToApplyOfferOn);
        BigDecimal priceOfOneUnit = productCatalogue.getPrice(productToApplyOfferOn);
        BigDecimal discount = new BigDecimal(productsEligibleForOffer.size() / 2).multiply(priceOfOneUnit);
        return discount;
    }
}
