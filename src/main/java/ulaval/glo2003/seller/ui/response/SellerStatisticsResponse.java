package ulaval.glo2003.seller.ui.response;

import ulaval.glo2003.product.ui.response.ProductStatisticsResponse;

import java.util.List;

public class SellerStatisticsResponse {
    private List<ProductStatisticsResponse> productsStatistics;

    public SellerStatisticsResponse(List<ProductStatisticsResponse> products) {
        this.productsStatistics = products;
    }

    public SellerStatisticsResponse() {
    }

    public List<ProductStatisticsResponse> getProductsStatistics() {
        return productsStatistics;
    }
}
