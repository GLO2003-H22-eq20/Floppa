package ulaval.glo2003.product.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.product.ui.request.ProductRequest;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductFactory;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.product.domain.SellerProduct;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.product.persistence.ProductInMemoryRepository;
import ulaval.glo2003.seller.persistence.SellerInMemoryRepository;

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
    private static final Float MIMINUM_PRICE = 7.7f;
    private static final Float MAXIMUM_PRICE = 7.7f;
    private final List<String> CATEGORIES = new ArrayList<>() {
        {
            add("beauty");
            add("housing");
        }
    };

    @Mock
    private ProductInMemoryRepository productRepository;
    @Mock
    private SellerInMemoryRepository sellerInMemoryRepository;
    @Mock
    private ProductFactory productFactory;
    @Mock
    private Product product;
    @Mock
    private Seller seller;

    ProductRequest request = new ProductRequest() {
        {
            title = "productTitle";
            description = "productDescription";
            suggestedPrice = 777.77f;
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
        productService = new ProductService(productRepository, sellerInMemoryRepository, productFactory);
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

        verify(productRepository).saveProduct(product);
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

        verify(sellerInMemoryRepository).findById(product.getSellerId());
    }

    @Test
    public void whenGettingAProduct_thenSearchesForTheProductInRepository() {
        givenAProductCanBeFound();
        givenASellerCanBeFound();

        productService.getProduct(anyString());

        verify(productRepository).findById(anyString());
    }

    @Test
    public void whenGettingAProduct_thenReturnsSellerProduct() {
        givenASellerCanBeFound();
        givenAProductCanBeFound();

        SellerProduct sellerProduct = productService.getProduct(anyString());

        assertNotNull(sellerProduct);
    }

    @Test
    public void whenGettingFilteredProducts_thenSearchesForFilteredProductsInRepository() {
        productService.getFilteredProducts(SELLER_ID, TITLE, CATEGORIES, MIMINUM_PRICE, MAXIMUM_PRICE);

        verify(productRepository).findFilteredProducts(anyString(), anyString(), any(), anyFloat(), anyFloat());
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
                anyFloat(),
                any());
        willReturn(PRODUCT_ID).given(product).getId();
        return product;
    }

    private void givenAProductCanBeFound() {
        willReturn(product).given(productRepository).findById(anyString());
    }

    private void givenASellerCanBeFound() {
        willReturn(seller).given(sellerInMemoryRepository).findById(SELLER_ID);
        willReturn(SELLER_ID).given(product).getSellerId();
    }
}