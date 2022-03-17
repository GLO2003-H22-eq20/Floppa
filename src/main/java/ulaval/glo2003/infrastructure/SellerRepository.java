package ulaval.glo2003.infrastructure;

import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Seller;

public interface SellerRepository {
    void saveSeller(Seller seller);

    Seller findById(String id);
}
