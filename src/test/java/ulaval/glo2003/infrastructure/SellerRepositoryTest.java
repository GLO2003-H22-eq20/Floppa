package ulaval.glo2003.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Seller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SellerRepositoryTest {
    private static final String ID = "50";
    private static final String NAME = "Carey Price";
    private static final String BIO = "Hockey";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1987, 8, 16);

    private SellerInMemoryRepository sellerInMemoryRepository;
    private Seller seller;

    @BeforeEach
    public void setUp() {
        sellerInMemoryRepository = new SellerInMemoryRepository();
        seller = new Seller(NAME, BIO, BIRTH_DATE);
    }

    @Test
    public void givenASeller_whenSaving_thenCanFindItById() {
        sellerInMemoryRepository.saveSeller(seller);

        Seller currentSeller = sellerInMemoryRepository.findById(seller.getId());
        assertEquals(seller, currentSeller);
    }

    @Test
    public void whenRetrievingNonExistantSeller_thenRetrievingByIdThrowsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> sellerInMemoryRepository.findById(ID));
    }
}