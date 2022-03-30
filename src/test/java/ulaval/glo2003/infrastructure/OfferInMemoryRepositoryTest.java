package ulaval.glo2003.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.offer.persistence.OfferInMemoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OfferInMemoryRepositoryTest {
    private static final String PRODUCT_ID = "1";
    private static final String NAME = "John Doe";
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String PHONENUMBER = "18191234567";
    private static final Double AMOUNT = 2000.333333;
    private static final String MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis "
            + "posuere. Maecenas vel lorem amet.";
    private static final Long ZERO = 0L;
    private static final Long ONE = 1L;

    @Spy
    private OfferInMemoryRepository offerInMemoryRepository;
    private Offer offer;

    @BeforeEach
    public void setUp() {
        offerInMemoryRepository = new OfferInMemoryRepository();
        offer = new Offer(PRODUCT_ID, NAME, EMAIL, PHONENUMBER, AMOUNT, MESSAGE);
    }

    @Test
    public void givenOffer_whenSaving_thenCanGetProductOffers() {
        offerInMemoryRepository.saveOffer(offer);

        assertEquals(offerInMemoryRepository.getOffers(PRODUCT_ID).getCount(), ONE);
        assertNotNull(offerInMemoryRepository.getOffers(PRODUCT_ID).getMean());
    }

    @Test
    public void whenRetrievingMissingProductOffers_thenReturnsValidOffers() {
        assertEquals(offerInMemoryRepository.getOffers(PRODUCT_ID).getCount(), ZERO);
        assertNull(offerInMemoryRepository.getOffers(PRODUCT_ID).getMean());
    }
}
