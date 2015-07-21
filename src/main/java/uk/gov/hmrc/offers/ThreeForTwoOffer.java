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

        BigDecimal discount = new BigDecimal(0.00);

        BigDecimal priceOfOneUnit = productCatalogue.getPrice(productToApplyOfferOn);
        for (int i = 1; i <= productsEligibleForOffer.size(); i++) {
            if(i % 3 == 0) {
                discount = discount.add(priceOfOneUnit);
            }
        }
        return discount;
    }
}
