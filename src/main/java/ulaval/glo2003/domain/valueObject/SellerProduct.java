package ulaval.glo2003.domain.valueObject;

import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.util.Objects;

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

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellerProduct that = (SellerProduct) o;
        return ((Objects.equals(seller, that.seller)) & (Objects.equals(product, that.product)));
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(seller)) & (Objects.hashCode(product)));
    }
}
