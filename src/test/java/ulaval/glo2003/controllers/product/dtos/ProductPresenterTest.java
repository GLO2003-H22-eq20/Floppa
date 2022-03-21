package ulaval.glo2003.controllers.product.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.ProductOffers;
import ulaval.glo2003.domain.valueObject.SellerProduct;

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
    private static final Double PRODUCT_SUGGESTED_PRICE = 1.;
    private static final List<ProductCategory> PRODUCT_CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };
    private static final Double OFFERS_MEAN = 20.5;
    private static final Long OFFERS_COUNT = 2L;

    private SellerProduct sellerProduct;

    private ProductPresenter productPresenter;

    @BeforeEach
    public void setUp() {
        productPresenter = new ProductPresenter();

        Seller seller = new Seller(SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE);
        Product product = new Product(seller.getId(),
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