package ulaval.glo2003.infrastructure;

import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Seller;

import java.util.*;

public class SellerRepository {
    private final Map<String, Seller> sellerMap = Collections.synchronizedMap(new HashMap<>());

    public void saveSeller(Seller seller) {
        sellerMap.put(seller.getId().toString(), seller);
    }

    public Seller findById(String id) {
        if (sellerMap.get(id) == null) {
            throw new ItemNotFoundException();
        }
        return sellerMap.get(id);
    }
}
