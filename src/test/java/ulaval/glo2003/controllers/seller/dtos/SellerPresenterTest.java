package ulaval.glo2003.controllers.seller.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerProducts;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SellerPresenterTest {
    private static final String ID = "1";
    private static final String NAME = "name";
    private static final String CREATED_AT = "2022-02-23T00:33:10.002215Z";
    private static final LocalDate BIRTHDATE = LocalDate.of(1996, 2, 3);
    private static final String BIO = "bio";
    private static final List<ProductResponse> CATEGORIES = null;
    @Mock
    List<Product> products;
    private SellerResponse sellerResponse;
    private SellerPresenter sellerPresenter;
    private Seller seller;
    private SellerProducts sellerProducts;

    @BeforeEach
    public void setUp() {
        sellerPresenter = new SellerPresenter();
        sellerResponse = new SellerResponse(ID, NAME, CREATED_AT, BIO, CATEGORIES);
        seller = new Seller(NAME, BIO, BIRTHDATE);
        sellerProducts = new SellerProducts(seller, products);
    }

    @Test
    public void givenASellerProducts_whenPresentingSeller_thenReturnedSellerResponse() {
        sellerResponse = sellerPresenter.presentSeller(sellerProducts);

        assertNotNull(sellerResponse.getId());
        assertEquals(sellerProducts.getSeller().getName(), sellerResponse.getName());
        assertEquals(sellerProducts.getSeller().getCreatedAt().toString(), sellerResponse.getCreatedAt());
        assertEquals(sellerProducts.getSeller().getBio(), sellerResponse.getBio());
        assertNotNull(sellerProducts.getProducts());
    }
}