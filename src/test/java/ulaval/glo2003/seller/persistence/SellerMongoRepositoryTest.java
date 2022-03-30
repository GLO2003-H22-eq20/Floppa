package ulaval.glo2003.seller.persistence;

import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.SellerRepositoryTest;

public class SellerMongoRepositoryTest extends SellerRepositoryTest {
    @Override
    protected SellerRepository createSellerRepository() {
        return new SellerMongoRepository(new DatastoreProvider(), new SellerModelAssembler());
    }
}
