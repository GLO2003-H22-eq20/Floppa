package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.exceptions.InvalidParameterException;
import ulaval.glo2003.exceptions.MissingParameterException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductFactoryTest {
    private static final String SELLER_ID = "1";
    private static final String TITLE = "titleOfProduct";
    private static final String DESCRIPTION = "descriptionOfProduct";
    private static final String BLANK_STRING = " ";
    private static final Double SUGGESTED_PRICE = 7.7;
    private static final Double INVALID_SUGGESTED_PRICE = 0.5;
    private static final int INITIAL_VIEWS_COUNT = 0;
    private final List<String> CATEGORIES = new ArrayList<>() {
        {
            add("beauty");
            add("housing");
        }
    };
    private final List<String> INVALID_CATEGORIES = new ArrayList<>() {
        {
            add("a");
            add("b");
        }
    };

    private ProductFactory productFactory;

    @BeforeEach
    public void setUp() {
        productFactory = new ProductFactory();
    }

    @Test
    public void givenEmptyTitle_whenCreatingProduct_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, BLANK_STRING, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenEmptyDescription_whenCreatingProduct_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, BLANK_STRING, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenSuggestedPriceIsLowerThanOne_whenCreatingProduct_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, INVALID_SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenCategoriesAreNotValid_whenCreatingProduct_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, INVALID_CATEGORIES));
    }

    @Test
    public void givenTitleIsNotInRequest_whenCreatingProduct_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, null, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenDescriptionIsNotInRequest_whenCreatingProduct_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, null, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenSuggestedPriceIsNotInRequest_whenCreatingProduct_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, null, CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenProductHasAnIdGenerated() {
        Product product = productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertNotNull(product.getId());
    }

    @Test
    public void whenCreatingProduct_thenProductHasATimeOfCreation() {
        Product product = productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertNotNull(product.getCreatedAt());
    }

    @Test
    public void whenCreatingProduct_thenProductHasAViewsCount() {
        Product product = productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertNotNull(product.getViewsCount());
    }

    @Test
    public void whenCreatingProduct_thenProductViewsCountIsSetToInitialValue() {
        Product product = productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertEquals(product.getViewsCount(), INITIAL_VIEWS_COUNT);
    }

}