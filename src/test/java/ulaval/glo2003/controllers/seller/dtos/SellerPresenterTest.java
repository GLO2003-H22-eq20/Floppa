package ulaval.glo2003.controllers.seller.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.domain.Offers;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.ProductOffers;
import ulaval.glo2003.domain.valueObject.SellerProducts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({MockitoExtension.class})
public class SellerPresenterTest {
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

    private SellerProducts sellerProducts;
    private Seller seller;
    private SellerPresenter sellerPresenter;

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
        Offers offers = new Offers(OFFERS_MEAN, OFFERS_COUNT);
        List<ProductOffers> productsOffers = products.stream()
                .map(product -> new ProductOffers(product, offers)).collect(Collectors.toList());
        sellerProducts = new SellerProducts(seller, productsOffers);
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
