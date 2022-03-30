package ulaval.glo2003.offer.domain;

import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.product.domain.Offers;

public interface OfferRepository {

    public void saveOffer(Offer offer);

    public Offers getOffers(String productId);

}
