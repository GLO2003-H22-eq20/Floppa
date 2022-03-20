package ulaval.glo2003.domain.valueObject;

import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;

public class ProductOffers {
    private final Product product;
    private final Offers offers;

    public ProductOffers(Product product, Offers offers) {
        this.product = product;
        this.offers = offers;
    }

    public Product getProduct() {
        return product;
    }

    public Offers getOffers() {
        return offers;
    }
}
