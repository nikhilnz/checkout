package uk.gov.hmrc;


import uk.gov.hmrc.offers.Offer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String> productsList = getProductsAsList(products);
        BigDecimal totalPrice = new BigDecimal(0.0);
        BigDecimal discount = new BigDecimal(0.0);

        for (String product : productsList) {
            BigDecimal price = productCatalogue.getPrice(product);
            totalPrice = totalPrice.add(price);
        }

        if (offerCatalogue != null) {
            for (Offer offer : offerCatalogue.getOffers()) {
                discount = discount.add(offer.apply(productCatalogue, productsList));
            }
        }

        return priceFormatter.format(totalPrice.subtract(discount));
    }

    private List<String> getProductsAsList(String[] products) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < products.length; i++) {
            String product = products[i];
            list.add(product);
        }
        return list;
    }

}
