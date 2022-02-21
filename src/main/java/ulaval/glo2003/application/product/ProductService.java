package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerProduct;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.*;
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
        Product product = productFactory.createProduct(sellerId, productRequest.title, productRequest.description,
                productRequest.suggestedPrice, productRequest.categories);

        productRepository.saveProduct(product);

        return product.getId().toString();
    }

    public SellerProduct getProduct(String id) {
        Product product = productRepository.findById(id);
        Seller seller = sellerRepository.findById(product.getSellerId());

        return new SellerProduct(seller, product);
    }

    public List<SellerProduct> getProductFiltered(String sellerId, String title,
                                                  List<ProductCategory> categories, Float minPrice,
                                                  Float maxPrice) {
        List<Product> products = productRepository.getProducts();
        if(Optional.ofNullable(sellerId).isPresent()) {
            products = filterBySellerId(sellerId, products);
        }
        if(Optional.ofNullable(title).isPresent()) {
            products = filterByTitle(title, products);
        }
        if(!categories.isEmpty()) {
            products = filterByCategories(categories, products);
        }
        if(Optional.ofNullable(minPrice).isPresent()) {
            products = filterByMinPrice(minPrice, products);
        }
        if(Optional.ofNullable(maxPrice).isPresent()) {
            products = filterByMaxPrice(maxPrice, products);
        }
        return products.stream()
                .map(product ->  new SellerProduct(sellerRepository.findById(product.getSellerId()), product))
                .collect(Collectors.toList());
    }

    private List<Product> filterBySellerId(String sellerId, List<Product> products) {
        return products.stream()
                .filter(product -> Objects.equals(product.getSellerId(), sellerId))
                .collect(Collectors.toList());
    }

    private List<Product> filterByTitle(String title, List<Product> products) {
        return products.stream()
                .filter(product -> product.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    private List<Product> filterByCategories(List<ProductCategory> categories, List<Product> products) {
        return products.stream()
                .filter(product -> categories.stream().anyMatch(category -> product.getCategories().contains(category)))
                .collect(Collectors.toList());
    }

    private List<Product> filterByMinPrice(Float minPrice, List<Product> products) {
        return products.stream()
                .filter(product -> product.getSuggestedPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    private List<Product> filterByMaxPrice(Float maxPrice, List<Product> products) {
        return products.stream()
                .filter(product -> product.getSuggestedPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

}
