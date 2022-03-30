package ulaval.glo2003.seller.persistence;

import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.SellerRepositoryTest;

public class SellerInMemoryRepositoryTest extends SellerRepositoryTest {

    @Override
    protected SellerRepository createSellerRepository() {
        return new SellerInMemoryRepository();
    }

}
