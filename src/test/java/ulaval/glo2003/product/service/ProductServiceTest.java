package ulaval.glo2003.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.offer.domain.OffersAssembler;
import ulaval.glo2003.product.domain.*;
import ulaval.glo2003.product.ui.request.ProductRequest;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    static final String PRODUCT_ID = "1";
    static final String SELLER_ID = "1";
    private static final String TITLE = "titleOfProduct";
    private static final Double MIMINUM_PRICE = 7.7;
    private static final Double MAXIMUM_PRICE = 7.7;
    private final List<String> CATEGORIES = new ArrayList<>() {
        {
            add("beauty");
            add("housing");
        }
    };

    @Mock
    private ProductRepository productRepository;
    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private OfferRepository offerRepository;
    @Mock
    private ProductFactory productFactory;
    @Mock
    private Product product;
    @Mock
    private Seller seller;
    @Mock
    private Offers offers;
    @Mock
    private Offer offer;
    @Mock
    private OffersAssembler offersAssembler;

    ProductRequest request = new ProductRequest() {
        {
            title = "productTitle";
            description = "productDescription";
            suggestedPrice = 777.77;
            categories = new ArrayList<>() {
                {
                    add("beauty");
                    add("housing");
                }
            };
        }
    };

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(
                productRepository,
                sellerRepository,
                offerRepository,
                productFactory,
                offersAssembler
        );
    }

    @Test
    public void whenCreatingAProduct_thenCreatesANewProduct() {
        givenNewProductCanBeCreated();

        productService.createProduct(SELLER_ID, request);

        verify(productFactory).createProduct(SELLER_ID,
                request.title,
                request.description,
                request.suggestedPrice,
                request.categories);
    }

    @Test
    public void whenCreatingAProduct_theSavesTheNewProduct() {
        Product product = givenNewProductCanBeCreated();

        productService.createProduct(SELLER_ID, request);

        verify(productRepository).save(product);
    }

    @Test
    public void whenCreatingAProduct_thenGeneratesAProductId() {
        givenNewProductCanBeCreated();

        String productId = productService.createProduct(SELLER_ID, request);

        assertNotNull(productId);
    }

    @Test
    public void whenGettingAProduct_thenSearchesForSellerInRepository() {
        givenAProductCanBeFound();

        productService.getProduct(anyString());

        verify(sellerRepository).findById(product.getSellerId());
    }

    @Test
    public void whenGettingAProduct_thenSearchesForTheProductInRepository() {
        givenAProductCanBeFound();
        givenASellerCanBeFound();

        productService.getProduct(anyString());

        verify(productRepository).findById(anyString());
    }

    @Test
    public void whenGettingAProduct_thenSearchesForOffersInRepository() {
        givenAProductCanBeFound();
        givenOffersCanBeFound();
        givenOffersCanBeAssembled();

        productService.getProduct(anyString());

        verify(offerRepository).getOffersBy(anyString());
    }

    @Test
    public void whenGettingAProduct_thenAssembleOffers() {
        givenAProductCanBeFound();
        givenOffersCanBeFound();
        givenOffersCanBeAssembled();


        productService.getProduct(anyString());

        verify(offersAssembler).assembleOffers(anyList());
    }

    @Test
    public void whenGettingAProduct_thenReturnsSellerProduct() {
        givenASellerCanBeFound();
        givenAProductCanBeFound();
        givenOffersCanBeFound();
        givenOffersCanBeAssembled();


        SellerProduct sellerProduct = productService.getProduct(anyString());

        assertNotNull(sellerProduct);
    }

    @Test
    public void whenGettingAProduct_thenIncreaseViewCount() {
        givenAProductCanBeFound();
        givenOffersCanBeFound();
        givenOffersCanBeAssembled();

        productService.getProduct(anyString());

        verify(product).increaseViewCount();
    }

    @Test
    public void whenGettingFilteredProducts_thenSearchesForFilteredProductsInRepository() {
        productService.getFilteredProducts(SELLER_ID, TITLE, CATEGORIES, MIMINUM_PRICE, MAXIMUM_PRICE);

        verify(productRepository).findFilteredProducts(anyString(), anyString(), any(), anyDouble(), anyDouble());
    }

    @Test
    public void whenGettingFilteredProducts_thenReturnsListOfSellerProduct() {
        List<SellerProduct> sellerProductList =  productService.getFilteredProducts(SELLER_ID,
                TITLE,
                CATEGORIES,
                MIMINUM_PRICE,
                MAXIMUM_PRICE);

        assertNotNull(sellerProductList);
    }

    private Product givenNewProductCanBeCreated() {
        willReturn(product).given(productFactory).createProduct(anyString(),
                anyString(),
                anyString(),
                anyDouble(),
                any());
        willReturn(PRODUCT_ID).given(product).getId();
        return product;
    }

    private void givenAProductCanBeFound() {
        willReturn(product).given(productRepository).findById(anyString());
    }

    private void givenASellerCanBeFound() {
        willReturn(seller).given(sellerRepository).findById(SELLER_ID);
        willReturn(SELLER_ID).given(product).getSellerId();
    }

    private void givenOffersCanBeFound() {
        willReturn(List.of(offer)).given(offerRepository).getOffersBy(anyString());
    }

    private void givenOffersCanBeAssembled() {
        willReturn(offers).given(offersAssembler).assembleOffers(anyList());
    }
}