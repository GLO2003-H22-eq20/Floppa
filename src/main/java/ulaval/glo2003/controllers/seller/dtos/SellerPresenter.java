package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.domain.valueObject.ProductOffers;
import ulaval.glo2003.domain.valueObject.SellerProducts;

import java.util.List;
import java.util.stream.Collectors;

public class SellerPresenter {
    public SellerResponse presentSeller(SellerProducts sellerProducts) {
        return new SellerResponse(sellerProducts.getSeller().getId(),
                sellerProducts.getSeller().getName(),
                sellerProducts.getSeller().getCreatedAt().toString(),
                sellerProducts.getSeller().getBio(),
                presentProducts(sellerProducts.getProducts()));
    }

    private List<ProductResponse> presentProducts(List<ProductOffers> productsOffers) {
        return productsOffers.stream()
                .map(productOffers ->
                        new ProductResponse(productOffers.getProduct().getId(),
                                productOffers.getProduct().getCreatedAt().toString(),
                                productOffers.getProduct().getTitle(),
                                productOffers.getProduct().getDescription(),
                                productOffers.getProduct().getSuggestedPrice(),
                                productOffers.getOffers(),
                                productOffers.getProduct().getCategories().stream()
                                        .map(Enum::toString)
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
