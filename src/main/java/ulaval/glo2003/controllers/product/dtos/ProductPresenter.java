package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProduct;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPresenter {

    public ProductResponse presentProduct(SellerProduct sellerProduct) {
        return new ProductResponse(sellerProduct.getProduct().getId(),
                                   sellerProduct.getProduct().getCreatedAt(),
                                   sellerProduct.getProduct().getTitle(),
                                   sellerProduct.getProduct().getDescription(),
                                   sellerProduct.getProduct().getSuggestedPrice(),
                                   sellerProduct.getProduct().getOffer(),
                                   sellerProduct.getProduct().getCategories().stream()
                                                                             .map(Enum::toString)
                                                                             .collect(Collectors.toList()),
                                   presentSeller(sellerProduct.getSeller()));
    }

    public SellerResponse presentSeller(Seller seller) {
        return new SellerResponse(seller.getId().toString(), seller.getName());
    }

    public ProductsResponse presentProducts(List<SellerProduct> sellersProducts) {
        return new ProductsResponse(sellersProducts.stream()
                                                   .map(this::presentProduct)
                                                   .collect(Collectors.toList()));
    }
}
