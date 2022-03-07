package ulaval.glo2003.controllers.seller.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProducts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({MockitoExtension.class})
public class SellerPresenterTest {
    private static final String SELLER_NAME = "testSellerName";
    private static final LocalDate SELLER_BIRTHDATE = LocalDate.of(1996, 2, 3);
    private static final String SELLER_BIO = "testSellerBio";
    private static final String PRODUCT_TITLE = "testProductTitle";
    private static final String PRODUCT_DESCRIPTION = "testProductDescription";
    private static final Float PRODUCT_SUGGESTED_PRICE = 1.0f;
    private static final List<ProductCategory> PRODUCT_CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };

    private SellerPresenter sellerPresenter;
    private SellerProducts sellerProducts;

    @Mock
    private Seller seller;

    @BeforeEach
    public void setUp() {
        sellerPresenter = new SellerPresenter();

        seller = new Seller(SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE);
        List<Product> products = new ArrayList<>() {
            {
                add(new Product(seller.getId(),
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
        SellerResponse sellerResponse = sellerPresenter.presentSeller(sellerProducts);

        assertEquals(sellerResponse.getId(), sellerProducts.getSeller().getId());
        assertEquals(sellerResponse.getName(), sellerProducts.getSeller().getName());
        assertEquals(sellerResponse.getCreatedAt(), sellerProducts.getSeller().getCreatedAt().toString());
        assertEquals(sellerResponse.getBio(), sellerProducts.getSeller().getBio());
        assertNotNull(sellerResponse.getProducts());
    }
}
