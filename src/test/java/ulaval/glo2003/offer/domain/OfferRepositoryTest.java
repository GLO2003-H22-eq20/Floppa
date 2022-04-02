package ulaval.glo2003.offer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;


public abstract class OfferRepositoryTest {
    private static final UUID OFFER_ID = UUID.randomUUID();
    private static final String PRODUCT_ID = UUID.randomUUID().toString();
    private static final String NAME = "John Doe";
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String PHONENUMBER = "18191234567";
    private static final Double AMOUNT = 2000.333333;
    private static final String MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis "
            + "posuere. Maecenas vel lorem amet.";
    private static final Instant CREATED_AT = Instant.now();
    private static final int ZERO = 0;
    private static final int ONE = 1;

    @Spy
    private OfferRepository offerRepository;
    private Offer offer;

    protected abstract OfferRepository createOfferRepository();

    @BeforeEach
    public void setUp() {
        offerRepository = createOfferRepository();
        offer = new Offer(OFFER_ID, PRODUCT_ID, NAME, EMAIL, PHONENUMBER, AMOUNT, MESSAGE, CREATED_AT);
    }

//    @Test
//    public void givenSavedOffer_whenGettingProductOffers_thenReturnsOffer() {
//        offerRepository.save(offer);
//
//        List<Offer> offerList = offerRepository.getOffersBy(offer.getProductId());
//
//        assertThat(offerList).contains(offer);
//    }

    @Test
    public void givenNoOfferForProduct_whenGettingProductOffers_thenReturnsNone() {
        String productIdWithNoOffer = UUID.randomUUID().toString();

        List<Offer> offerList = offerRepository.getOffersBy(productIdWithNoOffer);

        assertThat(offerList).isEmpty();
    }
}
