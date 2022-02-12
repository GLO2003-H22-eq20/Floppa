package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;

import java.util.List;

public class ProductFactory {
    public Product createProduct(String title, String description, Float suggestedPrice, List<ProductCategory> categories) {
        validateTitle(title);
        validateDescription(description);
        validateSuggestedPrice(suggestedPrice);
        return new Product(title, description, suggestedPrice, categories);
    }

    private void validateTitle(String title) {
        if (title.isEmpty()) {
            throw new InvalidParameterException("Title is empty");
        }
    }

    private void validateDescription(String description) {
        if (description.isEmpty()) {
            throw new InvalidParameterException("Description is empty");
        }
    }

    private void validateSuggestedPrice(Float suggestedPrice) {
        if (suggestedPrice < 1.0) {
            throw new InvalidParameterException("Suggested price is less than 1.0");
        }
    }
}
