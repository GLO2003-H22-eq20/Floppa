package ulaval.glo2003.application.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.application.product.ProductFactoryTest;
import ulaval.glo2003.controllers.offer.dtos.OfferRequest;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.infrastructure.OfferRepository;
import ulaval.glo2003.infrastructure.ProductRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    private static final String PRODUCT_ID = "1";
    private static final String NAME = "John Doe";
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String PHONENUMBER = "18191234567";
    private static final Double AMOUNT = 2000.333333;
    private static final String MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis "
            + "posuere. Maecenas vel lorem amet.";

    @Mock
    private Product product;
    @Mock
    private Offer offer;
    @Mock
    private OfferRepository offerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OfferFactory offerFactory;
    @InjectMocks
    private OfferService offerService;

    OfferRequest request = new OfferRequest() {
        {
            name = NAME;
            email = EMAIL;
            phoneNumber = PHONENUMBER;
            amount = AMOUNT;
            message = MESSAGE;
        }
    };

    @BeforeEach
    public void setUp() {
        offerService = new OfferService(offerRepository, productRepository, offerFactory);
    }

    @Test
    public void whenCreatingNewOffer_thenCreatesNewOffer() {
        givenNewOfferCanBeCreated();
        givenProductCanBeFound();

        offerService.createOffer(PRODUCT_ID, request);

        verify(offerFactory)
                .createOffer(
                        product,
                        NAME,
                        EMAIL,
                        PHONENUMBER,
                        AMOUNT,
                        MESSAGE
                );
    }

    @Test
    public void whenCreatingNewOffer_thenSavesNewOffer() {
        givenNewOfferCanBeCreated();
        givenProductCanBeFound();

        offerService.createOffer(PRODUCT_ID, request);

        verify(offerRepository).saveOffer(offer);
    }

    @Test
    private void givenNewOfferCanBeCreated() {
        willReturn(offer).given(offerFactory)
                .createOffer(
                        any(Product.class),
                        anyString(),
                        anyString(),
                        anyString(),
                        anyDouble(),
                        anyString());
    }

    @Test
    private void givenProductCanBeFound() {
        willReturn(product).given(productRepository).findById(PRODUCT_ID);
    }
}
