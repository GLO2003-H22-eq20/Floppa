package ulaval.glo2003;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    public Seller findSeller(String id) {
        for(Seller seller : sellerList) {
            if(Objects.equals(seller.id.toString(), id)) {
                return seller;
            }
        }
        return null;
    }

    private boolean checkIfSellerExists(Seller seller) {
        return sellerList.stream().anyMatch(savedSeller -> savedSeller.id == seller.id );
    }
}
