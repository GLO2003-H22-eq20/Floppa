package ulaval.glo2003.domain.valueObject;

import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.util.List;

public class SellerProducts {
    private final Seller seller;
    private final List<Product> products;

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
