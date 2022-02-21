package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerProduct;

public class ProductPresenter {

    public ProductResponse presentProduct(SellerProduct sellerProduct) {
        return new ProductResponse(sellerProduct.getProducts().getId(), sellerProduct.getProducts().getCreatedAt(),
                sellerProduct.getProducts().getTitle(), sellerProduct.getProducts().getDescription(),
                sellerProduct.getProducts().getSuggestedPrice(), sellerProduct.getProducts().getOffer(),
                sellerProduct.getProducts().getCategories(), presentSeller(sellerProduct.getSeller()));
    }

    public SellerResponse presentSeller(Seller seller){
        return new SellerResponse(seller.getId().toString(), seller.getName());
    }
}
