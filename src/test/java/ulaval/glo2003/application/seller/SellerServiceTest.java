package ulaval.glo2003.application.seller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProducts;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {
    static final String SELLER_ID = "1";

    @Mock
    SellerFactory sellerFactory;
    @Mock
    SellerRepository sellerRepository;
    @Mock
    ProductRepository productRepository;

    SellerRequest request = new SellerRequest() {{
        name = "name";
        bio = "bio";
        birthDate = LocalDate.of(1996, 1, 12);
    }};

    @InjectMocks
    private SellerService sellerService;

    @BeforeEach
    public void setUp() {
        sellerService = new SellerService(sellerRepository, productRepository, sellerFactory);
    }

    @Test
    public void givenASeller_whenCreating_thenCreatesANewSeller() {
        givenNewSellerCanBeCreated();

        sellerService.createSeller(request);

        verify(sellerFactory).createSeller(request.name, request.bio, request.birthDate);
    }

    @Test
    public void givenASeller_whenCreating_thenSavesTheNewSeller() {
        Seller seller = givenNewSellerCanBeCreated();

        sellerService.createSeller(request);

        verify(sellerRepository).saveSeller(seller);
    }

    @Test
    public void givenASeller_whenCreating_thenGeneratesASellerId() {
        givenNewSellerCanBeCreated();

        String sellerId = sellerService.createSeller(request);

        assertNotNull(sellerId);
    }

    @Test
    public void givenASellerId_whenGettingASeller_thenSearchesForTheSellerInRepository() {
        givenASellerCanBeFound();
        givenProductsExist();

        sellerService.getSeller(anyString());

        verify(sellerRepository).findById(anyString());
    }

    @Test
    public void givenASellerId_whenGettingASeller_thenSearchesForTheProductsInRepository() {
        givenASellerCanBeFound();
        givenProductsExist();

        sellerService.getSeller(anyString());

        verify(productRepository).findProductsBySellerId(anyString());
    }

    @Test
    public void givenASellerId_whenGettingASeller_thenReturnsSellerProducts() {
        Seller seller = givenASellerCanBeFound();
        List<Product> products = givenProductsExist();
        SellerProducts expectedSellerProducts = new SellerProducts(seller, products);

        SellerProducts sellerProducts = sellerService.getSeller(anyString());

        assertEquals(expectedSellerProducts, sellerProducts);
    }

    private Seller givenNewSellerCanBeCreated() {
        Seller seller = mock(Seller.class);
        willReturn(seller).given(sellerFactory).createSeller(anyString(), anyString(), any());
        willReturn(SELLER_ID).given(seller).getId();
        return seller;
    }

    private Seller givenASellerCanBeFound() {
        Seller seller = mock(Seller.class);
        willReturn(seller).given(sellerRepository).findById(anyString());
        return seller;
    }

    private List<Product> givenProductsExist() {
        List<Product> products = new ArrayList<>();
        willReturn(products).given(productRepository).findProductsBySellerId(anyString());
        return products;
    }
}