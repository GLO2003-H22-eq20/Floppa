package ulaval.glo2003.domain;

import java.util.List;

public class SellerProducts {
    private Seller seller;
    private List<Product> products;

    public SellerProducts(Seller seller, List<Product> products) {
        this.seller = seller;
        this.products = products;
    }

    public Seller getSeller() {
        return seller;
    }

    public List<Product> getProducts() {
        return products;
    }
}
