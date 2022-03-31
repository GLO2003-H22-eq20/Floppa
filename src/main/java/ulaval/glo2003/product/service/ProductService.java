package ulaval.glo2003.product.service;


import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.offer.domain.OffersAssembler;
import ulaval.glo2003.offer.domain.ProductOffers;
import ulaval.glo2003.product.domain.*;
import ulaval.glo2003.product.ui.request.ProductRequest;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final OfferRepository offerInMemoryRepository;
    private final ProductFactory productFactory;
    private final OffersAssembler offersAssembler;

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository,
                          OfferRepository offerRepository, ProductFactory productFactory,
                          OffersAssembler offersAssembler) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.offerInMemoryRepository = offerRepository;
        this.productFactory = productFactory;
        this.offersAssembler = offersAssembler;
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
        Offers offers = offersAssembler.assembleOffers(offerInMemoryRepository.getOffersBy(id));
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
                                new ProductOffers(
                                        product,
                                        offersAssembler.assembleOffers(
                                                offerInMemoryRepository.getOffersBy(product.getId()))
                                )
                        )
                )
                .collect(Collectors.toList());
    }
}
