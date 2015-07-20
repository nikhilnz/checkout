package uk.gov.hmrc;


import org.assertj.core.util.Arrays;
import uk.gov.hmrc.offers.Offer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Checkout {

    private PriceFormatter priceFormatter = new PriceFormatter();
    private ProductCatalogue productCatalogue;
    private OfferCatalogue offerCatalogue;

    public Checkout() {
        this(ProductCatalogue.DEFAULT_CATALOGUE, null);
    }

    public Checkout(ProductCatalogue productCatalogue, OfferCatalogue offerCatalogue) {
        this.productCatalogue = productCatalogue;
        this.offerCatalogue = offerCatalogue;
    }

    public String checkout(String[] products) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        if (offerCatalogue != null) {
            List<Offer> offers = offerCatalogue.getOffers();
            for (Offer offer : offers) {
                String[] productsEligibleForOffer = select(offer.getProduct(), products);
                if (productsEligibleForOffer.length > 0) {
                    totalPrice = totalPrice.add(offer.apply(productCatalogue, productsEligibleForOffer));
                }
            }
        }

        for (String product : products) {
            if (product != null) {
                BigDecimal price = productCatalogue.getPrice(product.toLowerCase());
                totalPrice = totalPrice.add(price);
            }
        }
        return priceFormatter.format(totalPrice);
    }

    private String[] select(String productToSelect, String[] products) {
        String[] productsList = products.clone();
        List<String> selectedList = new ArrayList<String>();
        for (int i = 0; i < productsList.length; i++) {
            String product = productsList[i];
            if (product != null && product.equalsIgnoreCase(productToSelect)) {
                selectedList.add(product);
                products[i] = null;
            }
        }
        String[] toReturn = new String[selectedList.size()];
        toReturn = selectedList.toArray(toReturn);
        return toReturn;
    }
}
