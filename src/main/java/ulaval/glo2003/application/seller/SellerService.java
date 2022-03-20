package ulaval.glo2003.application.seller;

import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.ProductOffers;
import ulaval.glo2003.domain.valueObject.SellerProducts;
import ulaval.glo2003.infrastructure.OfferRepository;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;
    private final SellerFactory sellerFactory;

    public SellerService(SellerRepository sellerRepository,
            ProductRepository productRepository,
            OfferRepository offerRepository,
            SellerFactory sellerFactory) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
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
                .stream().map(product -> new ProductOffers(product, offerRepository.getOffers(product.getId())))
                .collect(Collectors.toList());

        return new SellerProducts(seller, products);
    }
}
