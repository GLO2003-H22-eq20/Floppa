package ulaval.glo2003.offer.domain;


import java.util.List;

public interface OfferRepository {

    void saveOffer(Offer offer);

    List<Offer> getOffersBy(String productId);
}
