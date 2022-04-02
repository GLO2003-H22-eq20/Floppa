package ulaval.glo2003.offer.persistence;

import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.offer.domain.OfferRepositoryTest;

public class OfferInMemoryRepositoryTest extends OfferRepositoryTest {
    @Override
    protected OfferRepository createOfferRepository() {
        return new OfferInMemoryRepository();
    }
}
