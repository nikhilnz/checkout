package uk.gov.hmrc.offers;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import uk.gov.hmrc.ProductCatalogue;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BuyOneGetOneFree implements Offer {
    private String productToApplyOfferOn;

    public BuyOneGetOneFree(String productToApplyOfferOn) {
        this.productToApplyOfferOn = productToApplyOfferOn;
    }

    public String getProductToApplyOfferOn() {
        return productToApplyOfferOn;
    }

    @Override
    public BigDecimal apply(ProductCatalogue productCatalogue, List<String> products) {

        List<String> productsEligibleForOffer = ListUtils.select(products, new Predicate<String>() {
            @Override
            public boolean evaluate(String productName) {
                return productName.equalsIgnoreCase(productToApplyOfferOn);
            }
        });

        Iterator<String> it = products.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if(next.equalsIgnoreCase(productToApplyOfferOn)) {
                it.remove();
            }
        }

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
