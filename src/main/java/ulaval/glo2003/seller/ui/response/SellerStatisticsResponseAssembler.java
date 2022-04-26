package ulaval.glo2003.seller.ui.response;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.ui.response.ProductStatisticsResponse;
import ulaval.glo2003.product.ui.response.ProductStatisticsResponseAssembler;
import ulaval.glo2003.seller.domain.SellerProducts;

import java.util.List;
import java.util.stream.Collectors;

public class SellerStatisticsResponseAssembler {
    private ProductStatisticsResponseAssembler productStatisticsResponseAssembler;

    public SellerStatisticsResponseAssembler(ProductStatisticsResponseAssembler productStatisticsResponseAssembler) {
        this.productStatisticsResponseAssembler = productStatisticsResponseAssembler;
    }

    public SellerStatisticsResponse presentSellerStatistics(SellerProducts sellerProducts) {
        List<Product> products = sellerProducts.getProducts().stream()
                .map(it -> it.getProduct()).collect(Collectors.toList());

        List<ProductStatisticsResponse> productStatisticsResponses = productStatisticsResponseAssembler.presentProductsStatistics(products);

        return new SellerStatisticsResponse(productStatisticsResponses);
    }


}
