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
    public void givenASeller_whenNameIsEmpty_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                sellerFactory.createSeller("", BIO, VALID_BIRTH_DATE));
    }

    @Test
    public void givenASeller_whenBioIsEmpty_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                sellerFactory.createSeller(NAME, "", VALID_BIRTH_DATE));
    }

    @Test
    public void givenASeller_whenAgeIsUnder18YearsOld_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                sellerFactory.createSeller(NAME, BIO, INVALID_BIRTH_DATE));
    }

    @Test
    public void givenASeller_whenNameIsNotInTheRequest_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                sellerFactory.createSeller(null, BIO, INVALID_BIRTH_DATE));
    }

    @Test
    public void givenASeller_whenBioIsNotInTheRequest_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                sellerFactory.createSeller(NAME, null, INVALID_BIRTH_DATE));
    }

    @Test
    public void givenASeller_whenBirthDateIsNotInTheRequest_thenThrowsMissingParameterException() {
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

    @Test
    public void whenCreatingSeller_thenSellerReferencesToName() {
        Seller seller = sellerFactory.createSeller(NAME, BIO, VALID_BIRTH_DATE);

        assertEquals(NAME, seller.getName());
    }

    @Test
    public void whenCreatingSeller_thenSellerReferencesToBio() {
        Seller seller = sellerFactory.createSeller(NAME, BIO, VALID_BIRTH_DATE);

        assertEquals(BIO, seller.getBio());
    }

    @Test
    public void whenCreatingSeller_thenSellerReferencesToBirthDate() {
        Seller seller = sellerFactory.createSeller(NAME, BIO, VALID_BIRTH_DATE);

        assertEquals(VALID_BIRTH_DATE, seller.getBirthDate());
    }
}