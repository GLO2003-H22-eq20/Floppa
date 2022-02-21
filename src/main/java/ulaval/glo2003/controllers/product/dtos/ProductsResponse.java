package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.domain.SellerProduct;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsResponse {
    private final List<ProductResponse> productResponses;

    public ProductsResponse(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }

    public
    List<ProductResponse> getProducts() {
        return productResponses;
    }
}
