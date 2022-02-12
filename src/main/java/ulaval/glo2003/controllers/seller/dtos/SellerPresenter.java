package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.application.seller.dtos.SellerDto;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class SellerPresenter {
    public SellerResponse presentSeller(SellerDto sellerDto) {
        return new SellerResponse(sellerDto.getId(), sellerDto.getName(), sellerDto.getCreatedAt(), sellerDto.getBio(), presentProducts(sellerDto.getProducts()));
    }

    public List<ProductResponse> presentProducts(List<Product> productList) {
        return productList.stream().map(product -> new ProductResponse(product.getId(), product.getCreatedAt(), product.getTitle(), product.getDescription(), product.getSuggestedPrice(), new Offers(null, 0))).collect(Collectors.toList());
    }
}
