package ulaval.glo2003.seller.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.exceptions.ItemNotFoundException;
import ulaval.glo2003.exceptions.MissingParameterException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.google.common.truth.Truth.assertThat;

public abstract class SellerRepositoryTest {
    private static final UUID ID = UUID.randomUUID();
    private static final UUID NONE_EXISTING_ID = UUID.randomUUID();
    private static final String NAME = "Carey Price";
    private static final String BIO = "Hockey";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1987, 8, 16);
    private static final String BLANK_STRING = " ";
    private static final Instant CREATED_AT = Instant.now();

    private SellerRepository sellerRepository;
    private Seller seller;

    @BeforeEach
    public void setUp() {
        sellerRepository = createSellerRepository();
        seller = new Seller(ID, NAME, BIO, BIRTH_DATE, CREATED_AT);
    }

    @Test
    public void givenASeller_whenSaving_thenCanFindItById() {
        sellerRepository.save(seller);

        Seller currentSeller = sellerRepository.findById(seller.getId());
        assertEquals(seller, currentSeller);
    }

    @Test
    public void whenRetrievingNonExistantSeller_thenRetrievingByIdThrowsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> sellerRepository.findById(NONE_EXISTING_ID.toString()));
    }

    @Test
    public void givenIdIsBlank_whenRetrievingNonExistantSeller_thenRetrievingByIdThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () -> sellerRepository.findById(BLANK_STRING));
    }

    protected abstract SellerRepository createSellerRepository();
}