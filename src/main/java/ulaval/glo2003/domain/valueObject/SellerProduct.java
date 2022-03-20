package ulaval.glo2003.domain.valueObject;

import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

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
