package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProduct;

public class ProductPresenter {

    public ProductResponse presentProduct(SellerProduct sellerProduct) {
        return new ProductResponse(sellerProduct.getProduct().getId(),
                                    sellerProduct.getProduct().getCreatedAt(),
                                    sellerProduct.getProduct().getTitle(),
                                    sellerProduct.getProduct().getDescription(),
                                    sellerProduct.getProduct().getSuggestedPrice(),
                                    sellerProduct.getProduct().getOffer(),
                                    sellerProduct.getProduct().getCategories(),
                                    presentSeller(sellerProduct.getSeller()));
    }

    public SellerResponse presentSeller(Seller seller){
        return new SellerResponse(seller.getId().toString(), seller.getName());
    }
}
