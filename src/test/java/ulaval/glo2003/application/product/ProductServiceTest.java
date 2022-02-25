package ulaval.glo2003.application.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    static final String PRODUCT_ID = "7";
    static final String SELLER_ID = "1";

    @Mock
    ProductRepository productRepository;
    @Mock
    SellerRepository sellerRepository;
    @Mock
    ProductFactory productFactory;

    ProductRequest request = new ProductRequest() {{
        title = "productTitle";
        description = "productDescription";
        suggestedPrice = 777.77f;
        categories = null;
    }};

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productRepository, sellerRepository, productFactory);
    }

    @Test
    public void givenAProductRequest_whenCreating_thenCreatesANewProduct() {
        givenNewProductCanBeCreated();

        productService.createProduct(SELLER_ID, request);

        verify(productFactory).createProduct(SELLER_ID, request.title, request.description, request.suggestedPrice, request.categories);
    }

    @Test
    public void givenAProductRequest_whenCreating_theSavesTheNewProduct() {
        Product product = givenNewProductCanBeCreated();

        productService.createProduct(SELLER_ID, request);

        verify(productRepository).saveProduct(product);
    }

    @Test
    public void givenAProductRequest_whenCreating_thenGeneratesAProductId() {
        givenNewProductCanBeCreated();

        String productId = productService.createProduct(SELLER_ID, request);

        assertNotNull(productId);
    }

//    @Test
//    public void givenAProductId_whenGettingAProduct_thenSearchesForSellerInRepository() {
//        givenAProductCanBeFound();
//        givenASellerCanBeFound();
//
//        productService.getProduct(anyString());
//
//        verify(sellerRepository).findById(anyString());
//    }

    private Product givenNewProductCanBeCreated() {
        Product product = mock(Product.class);
        willReturn(product).given(productFactory).createProduct(anyString(), anyString(), anyString(), anyFloat(), any());
        willReturn(PRODUCT_ID).given(product).getId();
        return product;
    }

    private Product givenAProductCanBeFound() {
        Product product = mock(Product.class);
        willReturn(product).given(productRepository).findById(anyString());
        return product;
    }

//    private Seller givenASellerCanBeFound() {
//        Seller seller = mock(Seller.class);
//        Product product = mock(Product.class);
//        willReturn(product).given(product).getSellerId();
//        willReturn(seller).given(sellerRepository).findById(anyString());
//        return seller;
//    }
}