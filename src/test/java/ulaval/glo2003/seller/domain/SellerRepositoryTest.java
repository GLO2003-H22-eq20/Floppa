package ulaval.glo2003.seller.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class SellerRepositoryTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "Carey Price";
    private static final String BIO = "Hockey";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1987, 8, 16);
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

    protected abstract SellerRepository createSellerRepository();
}