package ulaval.glo2003.application.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductFactoryTest {
    private static final String SELLER_ID = "1d4f5g6h7j8k9l";
    private static final String TITLE = "titleOfProduct";
    private static final String DESCRIPTION = "descriptionOfProduct";
    private static final Float SUGGESTED_PRICE = 7.7f;
    private static final Float INVALID_SUGGESTED_PRICE = 0.5f;
    private final List<ProductCategory> CATEGORIES = null;

    private ProductFactory productFactory;

    @BeforeEach
    public void setUp() {
        productFactory = new ProductFactory();
    }

    @Test
    public void givenAProduct_whenTitleIsEmpty_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, "", DESCRIPTION, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenAProduct_whenDescriptionIsEmpty_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, "", SUGGESTED_PRICE, CATEGORIES));
    }


    @Test
    public void givenAProduct_whenTitleIsNotInTheRequest_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, null, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenAProduct_whenDescriptionIsNotInTheRequest_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, null, SUGGESTED_PRICE, CATEGORIES));
    }

    @Test
    public void givenAProduct_whenSuggestedPriceIsNotInTheRequest_thenThrowsMissingParameterException() {
        assertThrows(MissingParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, null, CATEGORIES));
    }

    @Test
    public void givenAProduct_whenSuggestedPriceIsLowerThanOne_thenThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () ->
                productFactory.createProduct(SELLER_ID, TITLE, DESCRIPTION, INVALID_SUGGESTED_PRICE, CATEGORIES));
    }
}