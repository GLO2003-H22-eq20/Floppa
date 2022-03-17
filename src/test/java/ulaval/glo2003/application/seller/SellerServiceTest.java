package ulaval.glo2003.application.seller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProducts;
import ulaval.glo2003.infrastructure.ProductInMemoryRepository;
import ulaval.glo2003.infrastructure.SellerInMemoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {
    static final String SELLER_ID = "1";

    @Mock
    private SellerFactory sellerFactory;
    @Mock
    private SellerInMemoryRepository sellerInMemoryRepository;
    @Mock
    private ProductInMemoryRepository productRepository;

    SellerRequest request = new SellerRequest() {
        {
            name = "name";
            bio = "bio";
            birthDate = LocalDate.of(1996, 1, 12);
        }
    };

    @InjectMocks
    private SellerService sellerService;

    @BeforeEach
    public void setUp() {
        sellerService = new SellerService(sellerInMemoryRepository, productRepository, sellerFactory);
    }

    @Test
    public void whenCreatingSeller_thenCreatesANewSeller() {
        givenNewSellerCanBeCreated();

        sellerService.createSeller(request);

        verify(sellerFactory).createSeller(request.name, request.bio, request.birthDate);
    }

    @Test
    public void whenCreatingSeller_thenSavesTheNewSeller() {
        Seller seller = givenNewSellerCanBeCreated();

        sellerService.createSeller(request);

        verify(sellerInMemoryRepository).saveSeller(seller);
    }

    @Test
    public void whenCreatingSeller_thenGeneratesASellerId() {
        givenNewSellerCanBeCreated();

        String sellerId = sellerService.createSeller(request);

        assertNotNull(sellerId);
    }

    @Test
    public void whenGettingASeller_thenSearchesForTheSellerInRepository() {
        givenASellerCanBeFound();
        givenProductsExist();

        sellerService.getSeller(anyString());

        verify(sellerInMemoryRepository).findById(anyString());
    }

    @Test
    public void whenGettingASeller_thenSearchesForTheProductsInRepository() {
        givenASellerCanBeFound();
        givenProductsExist();

        sellerService.getSeller(anyString());

        verify(productRepository).findProductsBySellerId(anyString());
    }

    @Test
    public void whenGettingASeller_thenReturnsSellerProducts() {
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
        willReturn(seller).given(sellerInMemoryRepository).findById(anyString());
        return seller;
    }

    private List<Product> givenProductsExist() {
        List<Product> products = new ArrayList<>();
        willReturn(products).given(productRepository).findProductsBySellerId(anyString());
        return products;
    }
}