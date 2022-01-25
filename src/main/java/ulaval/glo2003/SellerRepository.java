package ulaval.glo2003;

import java.util.ArrayList;
import java.util.List;

public class SellerRepository {
    private List<Seller> sellerList;

    public SellerRepository() {
        sellerList = new ArrayList<>();
    }

    public boolean addSeller(Seller newSeller) {
        if(!checkIfSellerExists(newSeller))
        {
            return sellerList.add(newSeller);
        }
        return false;
    }

    private boolean checkIfSellerExists(Seller seller) {
        return sellerList.stream().anyMatch(savedSeller -> savedSeller.id == seller.id );
    }
}
