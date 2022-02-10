package ulaval.glo2003.controllers.product;

import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.controllers.product.dtos.ProductSellerResponse;
import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

public class ProductPresenter {

    public ProductResponse presentProduct(Product product) {
        return new ProductResponse(product.getId(), product.getCreatedAt(), product.getTitle(), product.getDescription(), product.getSuggestedPrice(), new Offers(null, 0), presentSeller(product.getSeller()), product.getCategories());
    }

    public ProductSellerResponse presentSeller(Seller seller){
        return new ProductSellerResponse(seller.getId().toString(), seller.getName());
    }
}
