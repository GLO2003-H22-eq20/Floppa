package ulaval.glo2003.seller;

import ulaval.glo2003.seller.Seller;

public interface SellerRepository {
    void saveSeller(Seller seller);

    Seller findById(String id);
}
