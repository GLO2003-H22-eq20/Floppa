package ulaval.glo2003.seller.domain;

import ulaval.glo2003.product.domain.Product;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellerProducts that = (SellerProducts) o;
        return ((Objects.equals(seller, that.seller)) & (Objects.equals(products, that.products)));
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(seller)) & (Objects.hashCode(products)));
    }
}
