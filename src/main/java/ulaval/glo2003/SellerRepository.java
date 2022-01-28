package ulaval.glo2003;

import java.util.*;

public class SellerRepository {
    private final Map<String, Seller> sellerMap = Collections.synchronizedMap(new HashMap<>());

    public void saveSeller(Seller seller) {
        if (sellerMap.containsKey(seller.getId())) {
//            throw new Exception();
        }

        sellerMap.put(seller.getId().toString(), seller);
    }

    public Optional<Seller> findById(String id) {
        return Optional.ofNullable(sellerMap.get(id));
    }
}
