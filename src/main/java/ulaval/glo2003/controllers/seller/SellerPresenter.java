package ulaval.glo2003.controllers.seller;

import ulaval.glo2003.controllers.seller.dtos.OfferResponse;
import ulaval.glo2003.controllers.seller.dtos.ProductResponse;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class SellerPresenter {
     public SellerResponse presentSeller(Seller seller) {
        return new SellerResponse(seller.getId().toString(), seller.getName(), seller.getCreatedAt().toString(), seller.getBio(), presentProducts(seller.getProducts()));
    }

    public List<ProductResponse> presentProducts(List<Product> productList) {
        return productList.stream().map(product -> new ProductResponse(product.getId(), product.getCreatedAt(), product.getTitle(), product.getDescription(), product.getSuggestedPrice(), presentOffer(product.getOffer()))).collect(Collectors.toList());
    }

    public OfferResponse presentOffer(Offer offer) {
         return new OfferResponse(offer.getMean(), offer.getCount());
    }
}
