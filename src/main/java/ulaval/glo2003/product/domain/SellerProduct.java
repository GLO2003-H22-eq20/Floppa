package ulaval.glo2003.product.domain;


import ulaval.glo2003.offer.domain.ProductOffers;
import ulaval.glo2003.seller.domain.Seller;

public class SellerProduct {
    private final Seller seller;
    private final ProductOffers productOffers;

    public SellerProduct(Seller seller, ProductOffers productOffers) {
        this.seller = seller;
        this.productOffers = productOffers;
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return productOffers.getProduct();
    }

    public Offers getOffers() {
        return productOffers.getOffers();
    }
}
