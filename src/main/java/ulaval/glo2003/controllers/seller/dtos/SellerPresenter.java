package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.valueObject.SellerProducts;

import java.util.List;
import java.util.stream.Collectors;

public class SellerPresenter {
    public SellerResponse presentSeller(SellerProducts sellerProducts) {
        return new SellerResponse(sellerProducts.getSeller().getId().toString(),
                                  sellerProducts.getSeller().getName(),
                                  sellerProducts.getSeller().getCreatedAt().toString(),
                                  sellerProducts.getSeller().getBio(),
                                  presentProducts(sellerProducts.getProducts()));
    }

    public List<ProductResponse> presentProducts(List<Product> productList) {
        return productList.stream()
                          .map(product ->
                                  new ProductResponse(product.getId(),
                                                              product.getCreatedAt(),
                                                              product.getTitle(),
                                                              product.getDescription(),
                                                              product.getSuggestedPrice(),
                                                              product.getOffer(),
                                                              product.getCategories().stream()
                                                                                     .map(Enum::toString)
                                                                                     .collect(Collectors.toList())))
                          .collect(Collectors.toList());
    }
}
