package ulaval.glo2003.product.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.product.Product;
import ulaval.glo2003.product.ProductCategory;
import ulaval.glo2003.product.api.response.ProductPresenter;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.seller.Seller;
import ulaval.glo2003.product.application.SellerProduct;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductPresenterTest {
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

    private ProductPresenter productPresenter;
    private SellerProduct sellerProduct;

    @Mock
    private Seller seller;
    @Mock
    private Product product;

    @BeforeEach
    public void setUp() {
        productPresenter = new ProductPresenter();

        seller = new Seller(SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE);
        product = new Product(seller.getId(),
                PRODUCT_TITLE,
                PRODUCT_DESCRIPTION,
                PRODUCT_SUGGESTED_PRICE,
                PRODUCT_CATEGORIES);
        sellerProduct = new SellerProduct(seller, product);
    }

    @Test
    public void givenSellerProduct_whenPresenting_thenReturnProductResponse() {
        ProductResponse productResponse = productPresenter.presentProduct(sellerProduct);

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

        ProductsResponse productsResponse = productPresenter.presentProducts(sellerProductList);

        assertNotNull(productsResponse.getProducts());
    }
}