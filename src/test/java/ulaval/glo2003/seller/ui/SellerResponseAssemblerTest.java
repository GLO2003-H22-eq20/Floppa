package ulaval.glo2003.seller.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.ProductOffers;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerProducts;
import ulaval.glo2003.seller.ui.response.SellerResponse;
import ulaval.glo2003.seller.ui.response.SellerResponseAssembler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({MockitoExtension.class})
public class SellerResponseAssemblerTest {
    private static final UUID SELLER_ID = UUID.randomUUID();
    private static final String SELLER_NAME = "testSellerName";
    private static final LocalDate SELLER_BIRTHDATE = LocalDate.of(1996, 2, 3);
    private static final String SELLER_BIO = "testSellerBio";
    private static final Instant SELLER_CREATED_AT = Instant.now();
    private static final UUID PRODUCT_ID = UUID.randomUUID();
    private static final String PRODUCT_TITLE = "testProductTitle";
    private static final String PRODUCT_DESCRIPTION = "testProductDescription";
    private static final Instant PRODUCT_CREATED_AT = Instant.now();
    private static final Double PRODUCT_SUGGESTED_PRICE = 1.;
    private static final int VIEWS_COUNT = 0;
    private static final List<ProductCategory> PRODUCT_CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };
    private static final Double OFFERS_MEAN = 20.5;
    private static final int OFFERS_COUNT = 2;
    private static final double OFFERS_MIN = 2;
    private static final double OFFERS_MAX = 2;
    private static final List<Offer> OFFER_LIST = new ArrayList<>() {};

    private SellerResponseAssembler sellerResponseAssembler;
    private SellerProducts sellerProducts;
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
                        PRODUCT_CATEGORIES,
                        VIEWS_COUNT));
            }
        };
        Offers offers = new Offers(OFFERS_MEAN, OFFERS_COUNT, OFFERS_MIN, OFFERS_MAX, OFFER_LIST);
        List<ProductOffers> productsOffers = products.stream()
                .map(product -> new ProductOffers(product, offers)).collect(Collectors.toList());
        sellerProducts = new SellerProducts(seller, productsOffers);
    }

    @Test
    public void givenSellerProducts_whenPresentingSeller_thenReturnSellerResponse() {
        SellerResponse sellerResponse = sellerResponseAssembler.presentSeller(sellerProducts);

        assertEquals(sellerResponse.getId(), sellerProducts.getSeller().getId());
        assertEquals(sellerResponse.getName(), sellerProducts.getSeller().getName());
        assertEquals(sellerResponse.getCreatedAt(), sellerProducts.getSeller().getCreatedAt().toString());
        assertEquals(sellerResponse.getBio(), sellerProducts.getSeller().getBio());
        assertNotNull(sellerResponse.getProducts());
    }

    @Test
    public void givenSellerProducts_whenPresentingCurrentSeller_thenReturnSellerResponse() {
        SellerResponse sellerResponse = sellerResponseAssembler.presentCurrentSeller(sellerProducts);

        assertEquals(sellerResponse.getId(), sellerProducts.getSeller().getId());
        assertEquals(sellerResponse.getName(), sellerProducts.getSeller().getName());
        assertEquals(sellerResponse.getCreatedAt(), sellerProducts.getSeller().getCreatedAt().toString());
        assertEquals(sellerResponse.getBio(), sellerProducts.getSeller().getBio());
        assertEquals(sellerResponse.getBirthDate(), sellerProducts.getSeller().getBirthDate().toString());
        assertNotNull(sellerResponse.getProducts());
    }
}
