package ulaval.glo2003.product.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.offer.domain.ProductOffers;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.domain.SellerProduct;
import ulaval.glo2003.product.ui.response.ProductResponse;
import ulaval.glo2003.product.ui.response.ProductResponseAssembler;
import ulaval.glo2003.product.ui.response.ProductsResponse;
import ulaval.glo2003.seller.domain.Seller;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductResponseAssemblerTest {
    private static final UUID SELLER_ID = UUID.randomUUID();
    private static final String SELLER_NAME = "testSellerName";
    private static final LocalDate SELLER_BIRTHDATE = LocalDate.of(1996, 2, 3);
    private static final Instant SELLER_CREATED_AT = Instant.now();
    private static final String SELLER_BIO = "testSellerBio";
    private static final UUID PRODUCT_ID = UUID.randomUUID();
    private static final Instant PRODUCT_CREATED_AT = Instant.now();
    private static final String PRODUCT_TITLE = "testProductTitle";
    private static final String PRODUCT_DESCRIPTION = "testProductDescription";
    private static final Double PRODUCT_SUGGESTED_PRICE = 1.;
    private static final List<ProductCategory> PRODUCT_CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };
    private static final Double OFFERS_MEAN = 20.5;
    private static final Long OFFERS_COUNT = 2L;

    private ProductResponseAssembler productResponseAssembler;
    private SellerProduct sellerProduct;

    @Mock
    private Seller seller;
    @Mock
    private Product product;

    @BeforeEach
    public void setUp() {
        productResponseAssembler = new ProductResponseAssembler();

        seller = new Seller(SELLER_ID, SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE, SELLER_CREATED_AT);
        product = new Product(PRODUCT_ID,
                seller.getId(),
                PRODUCT_CREATED_AT,
                PRODUCT_TITLE,
                PRODUCT_DESCRIPTION,
                PRODUCT_SUGGESTED_PRICE,
                PRODUCT_CATEGORIES);
        Offers offers = new Offers(OFFERS_MEAN, OFFERS_COUNT);
        ProductOffers productOffers = new ProductOffers(product, offers);
        sellerProduct = new SellerProduct(seller, productOffers);
    }

    @Test
    public void givenSellerProduct_whenPresenting_thenReturnProductResponse() {
        ProductResponse productResponse = productResponseAssembler.presentProduct(sellerProduct);

        assertEquals(productResponse.getId(), sellerProduct.getProduct().getId());
        assertEquals(productResponse.getCreatedAt(), sellerProduct.getProduct().getCreatedAt().toString());
        assertEquals(productResponse.getTitle(), sellerProduct.getProduct().getTitle());
        assertEquals(productResponse.getDescription(), sellerProduct.getProduct().getDescription());
        assertEquals(productResponse.getSuggestedPrice(), sellerProduct.getProduct().getSuggestedPrice());
        assertNotNull(productResponse.getOffers());
        assertNotNull(productResponse.getCategories());
        assertNotNull(productResponse.getSeller());
    }

    @Test
    public void givenASellersProductsList_whenPresenting_thenReturnProductsResponse() {
        List<SellerProduct> sellerProductList = new ArrayList<>() {
            {
                add(sellerProduct);
            }
        };

        ProductsResponse productsResponse = productResponseAssembler.presentProducts(sellerProductList);

        assertNotNull(productsResponse.getProducts());
    }
}