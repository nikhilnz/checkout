package uk.gov.hmrc;

import uk.gov.hmrc.offers.Offer;

import java.util.List;
import java.util.Map;

public class OfferCatalogue {
    private List<Offer> offers;

    public OfferCatalogue(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
