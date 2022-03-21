package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.valueObject.ProductOffers;
import ulaval.glo2003.domain.valueObject.SellerProduct;
import ulaval.glo2003.infrastructure.OfferRepository;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final OfferRepository offerRepository;
    private final ProductFactory productFactory;

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository,
                          OfferRepository offerRepository, ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.offerRepository = offerRepository;
        this.productFactory = productFactory;
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
        Offers offers = offerRepository.getOffers(id);
        Seller seller = sellerRepository.findById(product.getSellerId());
        ProductOffers productOffers = new ProductOffers(product, offers);
        return new SellerProduct(seller, productOffers);
    }

    public List<SellerProduct> getFilteredProducts(String sellerId,
                                                   String title,
                                                   List<String> categories,
                                                   Double minPrice,
                                                   Double maxPrice) {
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
                .map(product -> new SellerProduct(sellerRepository.findById(product.getSellerId()),
                        new ProductOffers(product, offerRepository.getOffers(product.getId()))))
                .collect(Collectors.toList());
    }
}
