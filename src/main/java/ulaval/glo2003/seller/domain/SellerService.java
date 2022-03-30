package ulaval.glo2003.seller.domain;

import ulaval.glo2003.seller.ui.request.SellerRequest;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;

import java.util.List;

public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final SellerFactory sellerFactory;

    public SellerService(SellerRepository sellerRepository,
                         ProductRepository productRepository,
                         SellerFactory sellerFactory) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.sellerFactory = sellerFactory;
    }

    public String createSeller(SellerRequest sellerRequest) {
        Seller seller = sellerFactory.createSeller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);

        sellerRepository.saveSeller(seller);

        return seller.getId();
    }

    public SellerProducts getSeller(String id) {
        Seller seller = sellerRepository.findById(id);
        List<Product> products = productRepository.findProductsBySellerId(id);

        return new SellerProducts(seller, products);
    }
}
