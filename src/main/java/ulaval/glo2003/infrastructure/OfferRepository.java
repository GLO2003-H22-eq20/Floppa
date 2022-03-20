package ulaval.glo2003.infrastructure;

import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.domain.Offers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;

public class OfferRepository {
    private final Map<String, Offer> offerMap = Collections.synchronizedMap(new HashMap<>());

    public void saveOffer(Offer offer) {
        offerMap.put(offer.getId().toString(), offer);
    }

    public Long getCount(String productId) {
        return offerMap.values().stream().filter(offer -> Objects.equals(productId,offer.getProductId())).count();
    }

    public Double getMean(String productId) {
        OptionalDouble mean = offerMap.values().stream()
                .filter(offer -> Objects.equals(productId, offer.getProductId()))
                .mapToDouble(Offer::getAmount).average();
        if (mean.isPresent()) {
            return mean.getAsDouble();
        } else {
            return Double.NaN;
        }
    }

    public Offers getOffers(String productId) {
        var offersMean = getMean(productId);
        if (offersMean.isNaN()) {
            return new Offers(getCount(productId));
        } else {
            return new Offers(offersMean, getCount(productId));
        }
    }
}
