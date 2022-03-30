package ulaval.glo2003.seller.ui.response;

import ulaval.glo2003.product.ui.response.ProductResponse;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.domain.SellerProducts;

import java.util.List;
import java.util.stream.Collectors;

public class SellerResponseAssembler {
    public SellerResponse presentSeller(SellerProducts sellerProducts) {
        return new SellerResponse(sellerProducts.getSeller().getId(),
                sellerProducts.getSeller().getName(),
                sellerProducts.getSeller().getCreatedAt().toString(),
                sellerProducts.getSeller().getBio(),
                presentProducts(sellerProducts.getProducts()));
    }

    private List<ProductResponse> presentProducts(List<Product> productList) {
        return productList.stream()
                .map(product ->
                        new ProductResponse(product.getId(),
                                product.getCreatedAt().toString(),
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
