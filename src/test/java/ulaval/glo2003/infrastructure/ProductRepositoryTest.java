package ulaval.glo2003.infrastructure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRepositoryTest {
    private static final String SELLER_ID = "1";
    private static final String TITLE = "item";
    private static final String DESCRIPTION = "anItem";
    private static final Double SUGGESTED_PRICE = 1.;
    private static final List<ProductCategory> CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };
    private static final String INVALID_ID = UUID.randomUUID().toString();
    private static final String INVALID_TITLE = "NOT_VALID";
    private static final List<ProductCategory> INVALID_CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.SPORTS);
        }
    };
    private static final Double INVALID_MIN_PRICE = 2.0;
    private static final Double INVALID_MAX_PRICE = 0.5;
    private static final Integer ZERO = 0;
    private static final Integer ONE = 1;
    private static final Integer TWO = 2;
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
        Product anotherProductWithSameSellerId = new Product(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);
        productRepository.saveProduct(anotherProductWithSameSellerId);

        Collection<Product> products = productRepository.findProductsBySellerId(SELLER_ID);
        assertEquals(TWO, products.size());
    }

    @Test
    public void whenRetrievingProductBySellerId_thenReturnsEmptyListIfProductDoesNotExist() {
        Collection<Product> products = productRepository.findProductsBySellerId(SELLER_ID);

        assertTrue(products.isEmpty());
    }

    @Test
    public void givenProduct_whenFilteringProductsBySellerId_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);

        List<Product> filteredProducts = productRepository.findFilteredProducts(product.getSellerId(),
                null,
                List.of(),
                null,
                null);

        assertEquals(ONE, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidId_whenFilteringProductsBySellerId_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);

        List<Product> filteredProducts = productRepository.findFilteredProducts(INVALID_ID, null, List.of(), null, null);

        assertEquals(ZERO, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProduct_whenFilteringProductsByTitle_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);
        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                product.getTitle(),
                List.of(),
                null,
                null);

        assertEquals(ONE, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidTitle_whenFilteringProductsByTitle_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);

        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                INVALID_TITLE,
                List.of(),
                null,
                null);

        assertEquals(ZERO, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProduct_whenFilteringProductsByCategories_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);
        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                null,
                product.getCategories(),
                null,
                null);

        assertEquals(ONE, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidCategories_whenFilteringProductsByCategories_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);
        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                null,
                INVALID_CATEGORIES,
                null,
                null);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndValidMinPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);

        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                null,
                List.of(),
                SUGGESTED_PRICE,
                null);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidMinPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);

        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                null,
                List.of(),
                INVALID_MIN_PRICE,
                null);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndValidMaxPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);

        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                null,
                List.of(),
                null,
                SUGGESTED_PRICE);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidMaxPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productRepository.saveProduct(product);


        List<Product> filteredProducts = productRepository.findFilteredProducts(null,
                null,
                List.of(),
                null,
                INVALID_MAX_PRICE);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }
}