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

    public String getProductToApplyOfferOn() {
        return productToApplyOfferOn;
    }

    @Override
    public BigDecimal apply(ProductCatalogue productCatalogue, List<String> products) {

        List<String> productsEligibleForOffer = getProductsEligibleForOffer(products, getProductToApplyOfferOn());

        filterProducts(products, getProductToApplyOfferOn());

        if( productsEligibleForOffer.size() == 0) {
            return new BigDecimal(0.0);
        }

        BigDecimal priceOfOneUnit = productCatalogue.getPrice(productToApplyOfferOn.toLowerCase());
        if (productsEligibleForOffer.size() == 1) {
            return priceOfOneUnit;
        } else if(productsEligibleForOffer.size() % 2 == 0) {
            return priceOfOneUnit.multiply(new BigDecimal(productsEligibleForOffer.size() / 2));
        } else {
            return priceOfOneUnit.multiply(new BigDecimal(productsEligibleForOffer.size() / 2)).add(priceOfOneUnit);
        }

    }
}
