package ulaval.glo2003.infrastructure;

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
    private static final Float SUGGESTED_PRICE = 1.0f;
    private static final List<ProductCategory> CATEGORIES = new ArrayList<>() {
        {
            add(ProductCategory.APPAREL);
            add(ProductCategory.BEAUTY);
        }
    };

    private ProductInMemoryRepository productInMemoryRepository;
    private Product product;

    @BeforeEach
    public void setUp() {
        productInMemoryRepository = new ProductInMemoryRepository();
        product = new Product(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);
    }

    @Test
    public void givenProducts_whenSavingTwoProducts_thenCanRetrieveAListOfProductsWithASellerId() {
        productInMemoryRepository.saveProduct(product);
        Product anotherProductWithSameSellerId = new Product("1", "title", "anItem", 1.0f, CATEGORIES);
        productInMemoryRepository.saveProduct(anotherProductWithSameSellerId);

        Collection<Product> products = productInMemoryRepository.findProductsBySellerId(SELLER_ID);
        assertEquals(2, products.size());
    }

    @Test
    public void whenRetrievingProductBySellerId_thenReturnsEmptyListIfProductDoesNotExist() {
        Collection<Product> products = productInMemoryRepository.findProductsBySellerId(SELLER_ID);

        assertTrue(products.isEmpty());
    }

    @Test
    public void givenProduct_whenFilteringProductsBySellerId_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(product.getSellerId(),
                null,
                List.of(),
                null,
                null);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidId_whenFilteringProductsBySellerId_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        String invalidId = UUID.randomUUID().toString();

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(invalidId, null, List.of(), null, null);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProduct_whenFilteringProductsByTitle_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                product.getTitle(),
                List.of(),
                null,
                null);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidTitle_whenFilteringProductsByTitle_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        String invalidTitle = "NOT_VALID";

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                invalidTitle,
                List.of(),
                null,
                null);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProduct_whenFilteringProductsByCategories_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                null,
                product.getCategories(),
                null,
                null);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidCategories_whenFilteringProductsByCategories_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        List<ProductCategory> invalidCategories = new ArrayList<>() {
            {
                add(ProductCategory.SPORTS);
            }
        };
        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                null,
                invalidCategories,
                null,
                null);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndValidMinPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        float validMinPrice = 1.0f;

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                null,
                List.of(),
                validMinPrice,
                null);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidMinPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        float invalidMinPrice = 2.0f;

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                null,
                List.of(),
                invalidMinPrice,
                null);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndValidMaxPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        float validMaxPrice = 1.0f;

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                null,
                List.of(),
                null,
                validMaxPrice);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.stream().anyMatch(filteredProduct -> filteredProduct.equals(product)));
    }

    @Test
    public void givenProductAndInvalidMaxPrice_whenFilteringProductsByEmptyCategories_thenReturnFilteredProductList() {
        productInMemoryRepository.saveProduct(product);
        float invalidMaxPrice = 0.5f;

        List<Product> filteredProducts = productInMemoryRepository.findFilteredProducts(null,
                null,
                List.of(),
                null,
                invalidMaxPrice);

        assertEquals(0, filteredProducts.size());
        assertTrue(filteredProducts.stream().noneMatch(filteredProduct -> filteredProduct.equals(product)));
    }
}