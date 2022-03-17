package ulaval.glo2003.infrastructure;

import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Seller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SellerInMemoryRepository implements SellerRepository {
    private final Map<String, Seller> sellerMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void saveSeller(Seller seller) {
        sellerMap.put(seller.getId(), seller);
    }

    @Override
    public Seller findById(String id) {
        if (sellerMap.get(id) == null) {
            throw new ItemNotFoundException();
        }
        return sellerMap.get(id);
    }
}
