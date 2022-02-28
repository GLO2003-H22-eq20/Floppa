package ulaval.glo2003.application.seller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Seller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SellerFactoryTest {
    private static final String NAME = "Carey Price";
    private static final String BIO = "Hockey";
    private static final LocalDate VALID_BIRTH_DATE = LocalDate.of(1987, 8, 16);
    private static final LocalDate INVALID_BIRTH_DATE = LocalDate.of(2004, 8, 16);

    private SellerFactory sellerFactory;

    @BeforeEach
    public void setUp() {
        sellerFactory = new SellerFactory();
    }

    @Test
    public void whenCreatingSeller_thenIfNameIsEmptyThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                sellerFactory.createSeller("", BIO, VALID_BIRTH_DATE));
    }

    @Test
    public void whenCreatingSeller_thenIfBioIsEmptyThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                sellerFactory.createSeller(NAME, "", VALID_BIRTH_DATE));
    }

    @Test
    public void whenCreatingSeller_thenIfAgeIsUnder18YearsOldThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                sellerFactory.createSeller(NAME, BIO, INVALID_BIRTH_DATE));
    }

    @Test
    public void whenCreatingSeller_thenIfNameIsNotInTheRequestThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                sellerFactory.createSeller(null, BIO, INVALID_BIRTH_DATE));
    }

    @Test
    public void whenCreatingSeller_thenIfBioIsNotInTheRequestThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                sellerFactory.createSeller(NAME, null, INVALID_BIRTH_DATE));
    }

    @Test
    public void whenCreatingSeller_thenIfBirthDateIsNotInTheRequestThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                sellerFactory.createSeller(NAME, BIO, null));
    }

    @Test
    public void whenCreatingSeller_thenSellerHasAnIdGenerated() {
        Seller seller = sellerFactory.createSeller(NAME, BIO, VALID_BIRTH_DATE);

        assertNotNull(seller.getId());
    }

    @Test
    public void whenCreatingSeller_thenSellerHasATimeOfCreation() {
        Seller seller = sellerFactory.createSeller(NAME, BIO, VALID_BIRTH_DATE);

        assertNotNull(seller.getCreatedAt());
    }
}