package ulaval.glo2003.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;

class ProductRepositoryTest {
    private static final String SELLER_ID = "1";
    private static final String TITLE = "item";
    private static final String DESCRIPTION = "anItem";
    private static final Float SUGGESTED_PRICE = 1.0f;
    private static final List<ProductCategory> CATEGORIES = null;

    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        product = new Product(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);
    }

//    @Test
//    public void givenAProduct_whenSaving_thenCanFindIt() {
//        sellerRepository.saveSeller(seller);
//
//        Seller currentSeller = sellerRepository.findById(seller.getId().toString());
//        assertEquals(seller, currentSeller);
//    }
//
//    @Test
//    public void whenRetrievingInexistantSeller_thenRetrievingByIdThrowsItemNotFoundException() {
//        assertThrows(ItemNotFoundException.class, () -> sellerRepository.findById(anyString()));
//    }
}