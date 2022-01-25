package ulaval.glo2003;

import java.util.ArrayList;
import java.util.List;

public class SellerRepository {
    private List<Seller> sellerList;

    public SellerRepository() {
        sellerList = new ArrayList<>();
    }

    public int addSeller(Seller seller) {
        sellerList.add(seller);
        return sellerList.size();
    }
}
