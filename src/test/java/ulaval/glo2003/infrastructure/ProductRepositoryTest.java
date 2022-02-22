package ulaval.glo2003.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void givenProducts_whenSavingTwoProducts_thenCanRetrieveAListOfProductsWithASellerId() {
        productRepository.saveProduct(product);
        Product anotherProductWithSameSellerId = new Product("1", "title", "anItem", 1.0f, null);
        productRepository.saveProduct(anotherProductWithSameSellerId);

        Collection<Product> products = productRepository.findProductsBySellerId(SELLER_ID);
        assertEquals(2, products.size());
    }

    @Test
    public void whenRetrievingProductBySellerId_thenReturnsEmptyListIfProductDoesNotExist() {
        Collection<Product> products = productRepository.findProductsBySellerId(anyString());

        assertTrue(products.isEmpty());
    }
}