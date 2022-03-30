package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerProducts;
import ulaval.glo2003.seller.ui.response.SellerResponseAssembler;
import ulaval.glo2003.seller.ui.response.SellerResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({MockitoExtension.class})
public class SellerPresenterTest {
    private static final UUID SELLER_ID = UUID.randomUUID();
    private static final String SELLER_NAME = "testSellerName";
    private static final LocalDate SELLER_BIRTHDATE = LocalDate.of(1996, 2, 3);
    private static final String SELLER_BIO = "testSellerBio";
    private static final Instant SELLER_CREATED_AT = Instant.now();
    private static final UUID PRODUCT_ID = UUID.randomUUID();
    private static final String PRODUCT_TITLE = "testProductTitle";
    private static final String PRODUCT_DESCRIPTION = "testProductDescription";
    private static final Instant PRODUCT_CREATED_AT = Instant.now();
    private static final Float PRODUCT_SUGGESTED_PRICE = 1.0f;
    private static final List<ProductCategory> PRODUCT_CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };

    private SellerResponseAssembler sellerResponseAssembler;
    private SellerProducts sellerProducts;

    @Mock
    private Seller seller;

    @BeforeEach
    public void setUp() {
        sellerResponseAssembler = new SellerResponseAssembler();

        seller = new Seller(SELLER_ID, SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE, SELLER_CREATED_AT);
        List<Product> products = new ArrayList<>() {
            {
                add(new Product(PRODUCT_ID,
                        seller.getId(),
                        PRODUCT_CREATED_AT,
                        PRODUCT_TITLE,
                        PRODUCT_DESCRIPTION,
                        PRODUCT_SUGGESTED_PRICE,
                        PRODUCT_CATEGORIES));
            }
        };
        sellerProducts = new SellerProducts(seller, products);
    }

    @Test
    public void givenSellerProducts_whenPresenting_thenReturnSellerResponse() {
        SellerResponse sellerResponse = sellerResponseAssembler.presentSeller(sellerProducts);

        assertEquals(sellerResponse.getId(), sellerProducts.getSeller().getId());
        assertEquals(sellerResponse.getName(), sellerProducts.getSeller().getName());
        assertEquals(sellerResponse.getCreatedAt(), sellerProducts.getSeller().getCreatedAt().toString());
        assertEquals(sellerResponse.getBio(), sellerProducts.getSeller().getBio());
        assertNotNull(sellerResponse.getProducts());
    }
}
