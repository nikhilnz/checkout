package uk.gov.hmrc.offers;


import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.List;

public class ThreeForTwoOffer extends Offer {
    private String productToApplyOfferOn;

    public ThreeForTwoOffer(String productToApplyOfferOn) {
        this.productToApplyOfferOn = productToApplyOfferOn;
    }

    @Override
    public BigDecimal apply(ProductCatalogue productCatalogue, List<String> products) {
        List<String> productsEligibleForOffer = getProductsEligibleForOffer(products, productToApplyOfferOn);
        BigDecimal priceOfOneUnit = productCatalogue.getPrice(productToApplyOfferOn);
        BigDecimal discount = new BigDecimal(productsEligibleForOffer.size() / 3).multiply(priceOfOneUnit);
        return discount;
    }
}
