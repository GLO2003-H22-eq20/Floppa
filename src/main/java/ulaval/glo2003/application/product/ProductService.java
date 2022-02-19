package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerProducts;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;
    private final SellerRepository sellerRepository;

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository,
                          ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
        this.sellerRepository = sellerRepository;
    }

    public String createProduct(String sellerId, ProductRequest productRequest) {
        Product product = productFactory.createProduct(sellerId, productRequest.title, productRequest.description,
                productRequest.suggestedPrice, productRequest.categories);

        productRepository.saveProduct(product);

        return product.getId().toString();
    }

    public SellerProducts getProduct(String id) {
        List<Product> products = new ArrayList<>();
        Product product = productRepository.findById(id);
        products.add(product);
        Seller seller = sellerRepository.findById(product.getSellerId());

        return new SellerProducts(seller, products);
    }
}
