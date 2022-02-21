package ulaval.glo2003.domain;

import java.util.List;

public class SellerProduct {
    private final Seller seller;
    private final Product product;

    public SellerProduct(Seller seller, Product product) {
        this.seller = seller;
        this.product = product;
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProducts() {
        return product;
    }
}
