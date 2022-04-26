package ulaval.glo2003.seller.ui.response;

import ulaval.glo2003.product.ui.response.ProductStatisticsResponse;

import java.util.List;

public class SellerStatisticsResponse {

    private List<ProductStatisticsResponse> products;

    public SellerStatisticsResponse(List<ProductStatisticsResponse> products) {
        this.products = products;
    }

    public SellerStatisticsResponse() {}

    public List<ProductStatisticsResponse> getProductsStatistics() { return products; }
}
