package ulaval.glo2003.offer.service;

import ulaval.glo2003.offer.domain.OfferFactory;
import ulaval.glo2003.offer.persistence.OfferInMemoryRepository;
import ulaval.glo2003.offer.ui.request.OfferRequest;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;

public class OfferService {
    private final OfferInMemoryRepository offerInMemoryRepository;
    private final ProductRepository productRepository;
    private final OfferFactory offerFactory;

    public OfferService(OfferInMemoryRepository offerInMemoryRepository, ProductRepository productRepository,
                        OfferFactory offerFactory) {
        this.productRepository = productRepository;
        this.offerInMemoryRepository = offerInMemoryRepository;
        this.offerFactory = offerFactory;
    }

    public void createOffer(String productId, OfferRequest offerRequest) {
        Product product = productRepository.findById(productId);
        Offer offer = offerFactory.createOffer(product, offerRequest.name, offerRequest.email,
                offerRequest.phoneNumber, offerRequest.amount, offerRequest.message);
        offerInMemoryRepository.saveOffer(offer);
    }
}
