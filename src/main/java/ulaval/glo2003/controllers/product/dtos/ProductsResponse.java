package ulaval.glo2003.controllers.product.dtos;

import java.util.List;

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
