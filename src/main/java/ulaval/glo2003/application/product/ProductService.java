package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ProductFactory productFactory;

    public ProductService(ProductRepository productRepository,
                          SellerRepository sellerRepository,
                          ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.productFactory = productFactory;
    }

    public String createProduct(String sellerId, ProductRequest productRequest) {
        Product product = productFactory.createProduct(productRequest.title, productRequest.description,
                                                       productRequest.suggestedPrice, productRequest.categories);

        productRepository.saveProduct(product);
        Seller seller = sellerRepository.findById(sellerId);
        seller.addProduct(product);

        return product.getId().toString();
    }
}
