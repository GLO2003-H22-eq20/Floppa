package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProduct;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        sellerRepository.findById(sellerId);
        Product product = productFactory.createProduct(sellerId, productRequest.title, productRequest.description,
                productRequest.suggestedPrice, productRequest.categories);

        productRepository.saveProduct(product);

        return product.getId();
    }

    public SellerProduct getProduct(String id) {
        Product product = productRepository.findById(id);
        Seller seller = sellerRepository.findById(product.getSellerId());

        return new SellerProduct(seller, product);
    }

    public List<SellerProduct> getFilteredProducts(String sellerId,
                                                   String title,
                                                   List<String> categories,
                                                   Float minPrice,
                                                   Float maxPrice) {
        for (String category : categories) {
            if (!category.equals(category.toLowerCase(Locale.ROOT))) {
                throw new IllegalArgumentException();
            }
        }

        List<ProductCategory> productCategories = categories.stream()
                .map(category -> category.toUpperCase(Locale.ROOT))
                .map(ProductCategory::valueOf).collect(Collectors.toList());

        return productRepository.findFilteredProducts(sellerId, title, productCategories, minPrice, maxPrice)
                .stream()
                .map(product -> new SellerProduct(sellerRepository.findById(product.getSellerId()), product))
                .collect(Collectors.toList());
    }
}
