package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.infrastructure.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    public ProductService(ProductRepository productRepository,
                          ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    public String createProduct(String sellerId, ProductRequest productRequest) {
        Product product = productFactory.createProduct(sellerId, productRequest.title, productRequest.description,
                productRequest.suggestedPrice, productRequest.categories);

        productRepository.saveProduct(product);

        return product.getId();
    }
}
