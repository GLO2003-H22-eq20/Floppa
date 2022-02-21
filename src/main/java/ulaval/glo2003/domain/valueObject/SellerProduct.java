package ulaval.glo2003.domain.valueObject;

import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

public class SellerProduct {
    private final Seller seller;
    private final Product product;

    public SellerProduct(Seller seller, Product product){
        this.seller = seller;
        this.product = product;
    }

    public Seller getSeller() {return seller;}

    public Product getProduct() {return product;}
}
