package ulaval.glo2003.application.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Offer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfferFactoryTest {
    private static final String PRODUCT_ID = "1";
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

    // TODO: Finish up the invalid amount

    private static final String INVALID_MESSAGE_LENGTH = "Donec porttitor interdum lacus sed finibus. Nam pulvinar";

    private OfferFactory offerFactory;

    @BeforeEach
    public void setUp() {
        offerFactory = new OfferFactory();
    }

    @Test
    public void givenMissingName_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, null, EMAIL, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    public void givenMissingEmail_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, null, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    public void givenMissingPhoneNumber_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, EMAIL, null, AMOUNT, MESSAGE));
    }

    @Test
    void givenMissingAmount_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, EMAIL, PHONENUMBER, null, MESSAGE));
    }

    @Test
    void givenMissingMessage_whenCreatingOffer_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, EMAIL, PHONENUMBER, AMOUNT, null));
    }

    @Test
    void givenInvalidEmailMissingExt_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, INVALID_EMAIL_MISSING_EXT, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidEmailMissingDomain_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, INVALID_EMAIL_MISSING_DOMAIN, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidEmailMissingIdentifier_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(
                        PRODUCT_ID,
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
                offerFactory.createOffer(PRODUCT_ID, NAME, INVALID_EMAIL_MISSING_ALL, PHONENUMBER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidPhoneNumberLengthGreater_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(
                        PRODUCT_ID,
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
                offerFactory.createOffer(PRODUCT_ID, NAME, EMAIL, INVALID_PHONE_NUMBER_LENGTH_LESSER, AMOUNT, MESSAGE));
    }

    @Test
    void givenInvalidPhoneNumberContainsAlpha_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(
                        PRODUCT_ID,
                        NAME,
                        EMAIL,
                        INVALID_PHONE_NUMBER_CONTAINS_ALPHA,
                        AMOUNT,
                        MESSAGE
                )
        );
    }

    @Test
    void givenInvalidMessageLength_whenCreatingOffer_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                offerFactory.createOffer(PRODUCT_ID, NAME, EMAIL, PHONENUMBER, AMOUNT, INVALID_MESSAGE_LENGTH));
    }

    @Test
    public void whenCreatingOffer_thenOfferHasAnIdGenerated() {
        Offer offer = offerFactory.createOffer(PRODUCT_ID, NAME, EMAIL, PHONENUMBER, AMOUNT, MESSAGE);

        assertNotNull(offer.getId());
    }
}
