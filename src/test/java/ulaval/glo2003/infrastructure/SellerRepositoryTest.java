package ulaval.glo2003.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Seller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;

public class SellerRepositoryTest {
    private static final String NAME = "Carey Price";
    private static final String BIO = "Hockey";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1987, 8, 16);

    private SellerRepository sellerRepository;
    private Seller seller;

    @BeforeEach
    public void setUp() {
        sellerRepository = new SellerRepository();
        seller = new Seller(NAME, BIO, BIRTH_DATE);
    }

    @Test
    public void givenASeller_whenSaving_thenCanFindItById() {
        sellerRepository.saveSeller(seller);

        Seller currentSeller = sellerRepository.findById(seller.getId().toString());
        assertEquals(seller, currentSeller);
    }

    @Test
    public void whenRetrievingInexistantSeller_thenRetrievingByIdThrowsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> sellerRepository.findById(anyString()));
    }
}