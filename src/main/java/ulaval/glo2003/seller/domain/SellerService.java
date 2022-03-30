package ulaval.glo2003.seller.domain;



import ulaval.glo2003.offer.domain.ProductOffers;
import ulaval.glo2003.offer.persistence.OfferInMemoryRepository;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.seller.ui.request.SellerRequest;

import java.util.List;
import java.util.stream.Collectors;

public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final OfferInMemoryRepository offerInMemoryRepository;
    private final SellerFactory sellerFactory;

    public SellerService(SellerRepository sellerRepository,
            ProductRepository productRepository,
            OfferInMemoryRepository offerInMemoryRepository,
            SellerFactory sellerFactory) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.offerInMemoryRepository = offerInMemoryRepository;
        this.sellerFactory = sellerFactory;
    }

    public String createSeller(SellerRequest sellerRequest) {
        Seller seller = sellerFactory.createSeller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);

        sellerRepository.saveSeller(seller);

        return seller.getId();
    }

    public SellerProducts getSeller(String id) {
        Seller seller = sellerRepository.findById(id);
        List<ProductOffers> products = productRepository.findProductsBySellerId(id)
                .stream().map(product -> new ProductOffers(product, offerInMemoryRepository.getOffers(product.getId())))
                .collect(Collectors.toList());

        return new SellerProducts(seller, products);
    }
}
