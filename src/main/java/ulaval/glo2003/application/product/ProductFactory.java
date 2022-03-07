package ulaval.glo2003.application.product;

import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductFactory {
    private final float MINIMUM_SUGGESTED_PRICE = 1.0f;

    public Product createProduct(String sellerId,
                                 String title,
                                 String description,
                                 Float suggestedPrice,
                                 List<String> categories) {
        validateTitle(title);
        validateDescription(description);
        validateSuggestedPrice(suggestedPrice);
        validateCategories(categories);
        return new Product(sellerId, title, description, suggestedPrice, parseToProductCategory(categories));
    }

    private void validateTitle(String title) {
        if (title == null) {
            throw new MissingParameterException("Missing title");
        } else if (title.isEmpty()) {
            throw new InvalidParameterException("Title is empty");
        }
    }

    private void validateDescription(String description) {
        if (description == null) {
            throw new MissingParameterException("Missing description");
        } else if (description.isEmpty()) {
            throw new InvalidParameterException("Description is empty");
        }
    }

    private void validateSuggestedPrice(Float suggestedPrice) {
        if (suggestedPrice == null) {
            throw new MissingParameterException("Missing suggested price");
        } else if (suggestedPrice < MINIMUM_SUGGESTED_PRICE) {
            throw new InvalidParameterException("Suggested price is less than 1.0");
        }
    }

    private void validateCategories(List<String> categories) {
        if (categories == null) {
            throw new InvalidParameterException("Invalid category");
        }
        for (String category : categories) {
            try {
                if (!category.equals(category.toLowerCase(Locale.ROOT))) {
                    throw new InvalidParameterException("Invalid category");
                }
                ProductCategory.valueOf(category.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                throw new InvalidParameterException("Invalid category");
            }
        }
    }

    private List<ProductCategory> parseToProductCategory(List<String> categories) {
        return categories.stream()
                .map(category -> category.toUpperCase(Locale.ROOT))
                .map(ProductCategory::valueOf)
                .collect(Collectors.toList());
    }
}
