package ulaval.glo2003.application.offer;

import ulaval.glo2003.controllers.offer.dtos.OfferRequest;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.infrastructure.OfferRepository;
import ulaval.glo2003.infrastructure.ProductRepository;

public class OfferService {
    private final OfferRepository offerRepository;
    private final ProductRepository productRepository;
    private final OfferFactory offerFactory;

    public OfferService(OfferRepository offerRepository, ProductRepository productRepository,
                        OfferFactory offerFactory) {
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.offerFactory = offerFactory;
    }

    public void createOffer(String productId, OfferRequest offerRequest) {
        productRepository.findById(productId);
        Offer offer = offerFactory.createOffer(productId, offerRequest.name, offerRequest.email,
                offerRequest.phoneNumber, offerRequest.amount, offerRequest.message);
        offerRepository.saveOffer(offer);
    }
}
