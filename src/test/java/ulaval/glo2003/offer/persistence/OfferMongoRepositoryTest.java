package ulaval.glo2003.offer.persistence;

import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.offer.domain.OfferRepositoryTest;

public class OfferMongoRepositoryTest extends OfferRepositoryTest {
    @Override
    protected OfferRepository createOfferRepository() {
        return new OfferMongoRepository(new DatastoreProvider(), new OfferModelAssembler());
    }
}
