package ulaval.glo2003.seller.domain;

import ulaval.glo2003.offer.domain.ProductOffers;

import java.util.List;
import java.util.Objects;

public class SellerProducts {
    private final Seller seller;
    private final List<ProductOffers> productsOffers;

    public SellerProducts(Seller seller, List<ProductOffers> productsOffers) {
        this.seller = seller;
        this.productsOffers = productsOffers;
    }

    public Seller getSeller() {
        return seller;
    }

    public List<ProductOffers> getProducts() {
        return productsOffers;
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
        return ((Objects.equals(seller, that.seller)) & (Objects.equals(productsOffers, that.productsOffers)));
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(seller)) & (Objects.hashCode(productsOffers)));
    }
}
