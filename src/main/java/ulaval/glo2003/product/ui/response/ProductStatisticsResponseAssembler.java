package ulaval.glo2003.product.ui.response;

import ulaval.glo2003.product.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductStatisticsResponseAssembler {

    public ProductStatisticsResponse presentProductStatistics(Product product) {
        return new ProductStatisticsResponse(product.getId(), product.getTitle(), product.getViewsCount());
    }

    public List<ProductStatisticsResponse> presentProductsStatistics(List<Product> products) {
        return products.stream()
                .map(this::presentProductStatistics)
                .collect(Collectors.toList());
    }
}
