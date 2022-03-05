package ulaval.glo2003.controllers.seller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.controllers.seller.dtos.SellerPresenter;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProducts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class SellerPresenterTest {
    private static final String SELLER_NAME = "testSellerName";
    private static final LocalDate SELLER_BIRTHDATE = LocalDate.of(1996, 2, 3);
    private static final String SELLER_BIO = "testSellerBio";

    private static final String PRODUCT_TITLE = "testProductTitle";
    private static final String PRODUCT_DESCRIPTION = "testProductDescription";
    private static final Float PRODUCT_SUGGESTED_PRICE = 1.0f;
    private static final List<ProductCategory> PRODUCT_CATEGORIES = new ArrayList<>() {{
        add(ProductCategory.APPAREL);
        add(ProductCategory.BEAUTY);
    }};
    @Mock
    private Seller seller;
    @Mock
    private Product product;

    SellerPresenter sellerPresenter;
    SellerProducts sellerProducts;

    @BeforeEach
    void setUp() {
        sellerPresenter = new SellerPresenter();
        seller = new Seller(SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE);

        List<Product> products = new ArrayList<>();
        product = new Product(seller.getId(), PRODUCT_TITLE, PRODUCT_DESCRIPTION, PRODUCT_SUGGESTED_PRICE, PRODUCT_CATEGORIES);
        products.add(product);

        sellerProducts = new SellerProducts(seller, products);
    }

    @Test
    void givenSellerProducts_whenPresenting_thenReturnSellerResponse() {
        SellerResponse sellerResponse = sellerPresenter.presentSeller(sellerProducts);

        assertNotNull(sellerResponse);
        assertEquals(sellerResponse.getId(), seller.getId());
        assertEquals(sellerResponse.getName(), SELLER_NAME);
        assertEquals(sellerResponse.getCreatedAt(), seller.getCreatedAt().toString());
        assertEquals(sellerResponse.getBio(), SELLER_BIO);
    }

    @Test
    void givenProducts_whenPresenting_thenReturnProductResponses() {
        SellerResponse sellerResponse = sellerPresenter.presentSeller(sellerProducts);

        assertNotNull(sellerResponse);
        assertTrue(sellerResponse.getProducts().stream().anyMatch(product -> this.product.getId().equals(product.getId())));
        assertTrue(sellerResponse.getProducts().stream()
                .anyMatch(product -> this.product.getCreatedAt().toString().equals(product.getCreatedAt().toString())));
        assertTrue(sellerResponse.getProducts().stream()
                .anyMatch(product -> PRODUCT_SUGGESTED_PRICE.equals(product.getSuggestedPrice())));
        assertTrue(sellerResponse.getProducts().stream()
                .anyMatch(product -> PRODUCT_TITLE.equals(product.getTitle())));
        assertTrue(sellerResponse.getProducts().stream()
                .anyMatch(product -> PRODUCT_DESCRIPTION.equals(product.getDescription())));
        assertTrue(sellerResponse.getProducts().stream()
                .anyMatch(product -> PRODUCT_CATEGORIES.stream()
                        .map(ProductCategory::toString).collect(Collectors.toList()).equals(product.getCategories())));
    }
}
