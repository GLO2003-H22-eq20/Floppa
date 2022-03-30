package ulaval.glo2003.application.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.exceptions.InvalidParameterException;
import ulaval.glo2003.exceptions.MissingParameterException;
import ulaval.glo2003.offer.domain.OfferFactory;
import ulaval.glo2003.product.domain.Product;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferFactoryTest {
    private static final Double PRODUCT_SUGGESTED_AMOUNT = 2000.;
    private static final String NAME = "John Doe";
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String PHONENUMBER = "18191234567";
    private static final Double AMOUNT = 2000.333333;
    private static final String MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis "
            + "posuere. Maecenas vel lorem amet.";

    private static final String INVALID_EMAIL_MISSING_EXT = "myemail@com";
    private static final String INVALID_EMAIL_MISSING_DOMAIN = "myemail.com";
    private static final String INVALID_EMAIL_MISSING_IDENTIFIER = "@myemail.com";
    private static final String INVALID_EMAIL_MISSING_ALL = "@.";

    private static final String INVALID_PHONE_NUMBER_LENGTH_GREATER = "0".repeat(12);
    private static final String INVALID_PHONE_NUMBER_LENGTH_LESSER = "0".repeat(10);
    private static final String INVALID_PHONE_NUMBER_CONTAINS_ALPHA = "181912345AA";
    private static final Double INVALID_AMOUNT = 1999.99;
    private static final String INVALID_MESSAGE_LENGTH = "Donec porttitor interdum lacus sed finibus. Nam pulvinar";

    @Mock
    private Product product;

    private OfferFactory offerFactory;

    @BeforeEach
    public void setUp() {
        offerFactory = new OfferFactory();
    }

    @Test
    public void givenMissingName_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(product, null, EMAIL, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    public void givenMissingEmail_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(product, NAME, null, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    public void givenMissingPhoneNumber_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(product, NAME, EMAIL, null, AMOUNT, MESSAGE));
    }

    @Test
    void givenMissingAmount_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(product, NAME, EMAIL, PHONENUMBER, null, MESSAGE));
    }

    @Test
    void givenMissingMessage_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(product, NAME, EMAIL, PHONENUMBER, AMOUNT, null));
    }

    @Test
    void givenInvalidEmailMissingExt_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(product, NAME, INVALID_EMAIL_MISSING_EXT, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidEmailMissingDomain_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(product, NAME, INVALID_EMAIL_MISSING_DOMAIN, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidEmailMissingIdentifier_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(
                        product,
                        NAME,
                        INVALID_EMAIL_MISSING_IDENTIFIER,
                        PHONENUMBER,
                        AMOUNT,
                        MESSAGE
                )
        );
    }

    @Test
    void givenInvalidEmailMissingAll_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(product, NAME, INVALID_EMAIL_MISSING_ALL, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidPhoneNumberLengthGreater_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(
                        product,
                        NAME,
                        EMAIL,
                        INVALID_PHONE_NUMBER_LENGTH_GREATER,
                        AMOUNT,
                        MESSAGE
                )
        );
    }

    @Test
    void givenInvalidPhoneNumberLengthLesser_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(product, NAME, EMAIL, INVALID_PHONE_NUMBER_LENGTH_LESSER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidPhoneNumberContainsAlpha_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(
                        product,
                        NAME,
                        EMAIL,
                        INVALID_PHONE_NUMBER_CONTAINS_ALPHA,
                        AMOUNT,
                        MESSAGE
                )
        );
    }

    @Test
    void givenInvalidAmount_whenCreatingOffer_thenThrowsInvalidParameterException() {
        givenProductWithSuggestedAmount();

        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(product, NAME, EMAIL, PHONENUMBER, INVALID_AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidMessageLength_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(product, NAME, EMAIL, PHONENUMBER, AMOUNT, INVALID_MESSAGE_LENGTH));
    }

    @Test
    public void whenCreatingOffer_thenOfferHasAnIdGenerated() {
        givenProductWithSuggestedAmount();

        Offer offer = offerFactory.createOffer(product, NAME, EMAIL, PHONENUMBER, AMOUNT, MESSAGE);

        verify(product).getId();
        assertNotNull(offer.getId());
    }


    @Test
    private void givenProductWithSuggestedAmount() {
        when(product.getSuggestedPrice()).thenReturn(PRODUCT_SUGGESTED_AMOUNT);
    }
}
