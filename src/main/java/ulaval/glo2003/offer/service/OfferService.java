package ulaval.glo2003.offer.service;

import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferFactory;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.offer.ui.request.OfferRequest;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;

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
        Product product = productRepository.findById(productId);
        Offer offer = offerFactory.createOffer(product, offerRequest.name, offerRequest.email,
                offerRequest.phoneNumber, offerRequest.amount, offerRequest.message);
        offerRepository.save(offer);
    }
}
