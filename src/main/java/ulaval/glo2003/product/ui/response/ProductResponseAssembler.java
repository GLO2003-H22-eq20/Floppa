package ulaval.glo2003.product.ui.response;

import ulaval.glo2003.seller.ui.response.SellerResponse;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.product.domain.SellerProduct;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponseAssembler {

    public ProductResponse presentProduct(SellerProduct sellerProduct) {
        return new ProductResponse(sellerProduct.getProduct().getId(),
                sellerProduct.getProduct().getCreatedAt().toString(),
                sellerProduct.getProduct().getTitle(),
                sellerProduct.getProduct().getDescription(),
                sellerProduct.getProduct().getSuggestedPrice(),
                sellerProduct.getOffers(),
                sellerProduct.getProduct().getCategories().stream()
                        .map(Enum::toString)
                        .collect(Collectors.toList()),
                presentSeller(sellerProduct.getSeller()));
    }

    public ProductsResponse presentProducts(List<SellerProduct> sellersProducts) {
        return new ProductsResponse(sellersProducts.stream()
                .map(this::presentProduct)
                .collect(Collectors.toList()));
    }

    private SellerResponse presentSeller(Seller seller) {
        return new SellerResponse(seller.getId(), seller.getName());
    }
}
