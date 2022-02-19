package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerProducts;

public class ProductPresenter {

    public ProductResponse presentProduct(SellerProducts sellerProducts) {
        return new ProductResponse(sellerProducts.getProducts().get(0).getId(), sellerProducts.getProducts().get(0).getCreatedAt(), sellerProducts.getProducts().get(0).getTitle(), sellerProducts.getProducts().get(0).getDescription(), sellerProducts.getProducts().get(0).getSuggestedPrice(), sellerProducts.getProducts().get(0).getOffer(), sellerProducts.getProducts().get(0).getCategories(), presentSeller(sellerProducts.getSeller()));
    }

    public SellerResponse presentSeller(Seller seller){
        return new SellerResponse(seller.getId().toString(), seller.getName());
    }
}
