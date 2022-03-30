package ulaval.glo2003.seller.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.domain.valueObject.ProductOffers;
import ulaval.glo2003.infrastructure.OfferRepository;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.seller.domain.*;
import ulaval.glo2003.seller.ui.request.SellerRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private SellerRepository sellerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OfferRepository offerRepository;

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
        sellerService = new SellerService(sellerRepository, productRepository, offerRepository, sellerFactory);
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

        verify(sellerRepository).saveSeller(seller);
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

        verify(sellerRepository).findById(anyString());
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
        List<ProductOffers> productsOffers = products.stream()
                .map(product -> new ProductOffers(product, givenOffersCanBeFound())).collect(Collectors.toList());
        SellerProducts expectedSellerProducts = new SellerProducts(seller, productsOffers);

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

    private Offers givenOffersCanBeFound() {
        Offers offers =  mock(Offers.class);
        willReturn(offers).given(offerRepository).getOffers(anyString());
        return offers;
    }
}