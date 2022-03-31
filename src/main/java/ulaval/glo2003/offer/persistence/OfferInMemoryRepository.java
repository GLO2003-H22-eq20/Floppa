package ulaval.glo2003.offer.persistence;

import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferRepository;

import java.util.*;
import java.util.stream.Collectors;


public class OfferInMemoryRepository implements OfferRepository {

    private final Map<String, Offer> offerMap = Collections.synchronizedMap(new HashMap<>());

    public void saveOffer(Offer offer) {
        offerMap.put(offer.getId().toString(), offer);
    }

    public List<Offer> getOffersBy(String productId) {
        return offerMap.values().stream()
                .filter(offer -> Objects.equals(productId, offer.getProductId()))
                .collect(Collectors.toList());
    }
}
