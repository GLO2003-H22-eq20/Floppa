package ulaval.glo2003.application.seller.dtos;

import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class SellerAssembler {
    public SellerDto toDto(Seller seller) {
        return new SellerDto(seller.getId().toString(), seller.getName(), seller.getCreatedAt().toString(), seller.getBio(), presentProducts(seller.getProducts()));
    }

    public List<ProductResponse> presentProducts(List<Product> productList) {
        return productList.stream().map(product -> new ProductResponse(product.getId(), product.getCreatedAt(), product.getTitle(), product.getDescription(), product.getSuggestedPrice(), new Offers(null, 0))).collect(Collectors.toList());
    }
}
