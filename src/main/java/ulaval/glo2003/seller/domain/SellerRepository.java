package ulaval.glo2003.seller.domain;

import ulaval.glo2003.seller.domain.Seller;

public interface SellerRepository {
    void saveSeller(Seller seller);

    Seller findById(String id);
}
