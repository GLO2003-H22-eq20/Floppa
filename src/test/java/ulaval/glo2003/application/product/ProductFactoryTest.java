package ulaval.glo2003.application.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductFactoryTest {
    private static final String SELLER_ID = "1d4f5g6h7j8k9l";
    private static final String TITLE = "titleOfProduct";
    private static final String DESCRIPTION = "descriptionOfProduct";
    private static final Float SUGGESTED_PRICE = 7.7f;
    private static final Float INVALID_SUGGESTED_PRICE = 0.5f;
    private final List<String> CATEGORIES = new ArrayList<>() {{
        add("beauty");
        add("housing");
    }};
    private final List<String> INVALID_CATEGORIES = new ArrayList<>() {{
        add("a");
        add("b");
    }};

    private ProductFactory productFactory;

    @BeforeEach
    public void setUp() {
        productFactory = new ProductFactory();
    }

    @Test
    public void whenCreatingProduct_thenIfTitleIsEmptyThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, "", DESCRIPTION, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenIfDescriptionIsEmptyThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, "", SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenIfSuggestedPriceIsLowerThanOneThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, INVALID_SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenIfCategorieIsNotValidThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, INVALID_CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenIfTitleIsNotInTheRequestThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, null, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenIfDescriptionIsNotInTheRequestThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, null, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void whenCreatingProduct_thenIfSuggestedPriceIsNotInTheRequestThrowsMissingParameterException() {
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
    //TODO test parsing of categories
}