package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Product;

public class ProductAssembler {
    static public Product fromRequest(ProductRequest productRequest) {
        if (productRequest.title == null) {
            throw new MissingParameterException("Missing title");
        }
        if (productRequest.description == null) {
            throw new MissingParameterException("Missing description");
        }
        if (productRequest.suggestedPrice == null) {
            throw new MissingParameterException("Missing suggested price");
        }
        if (productRequest.title.isEmpty()) {
            throw new InvalidParameterException("Title is empty");
        }
        if (productRequest.description.isEmpty()) {
            throw new InvalidParameterException("Description is empty");
        }
        if (productRequest.suggestedPrice.get() < 1.0) {
            throw new InvalidParameterException("Suggested price is less than 1.0");
        }
        return new Product(productRequest.title, productRequest.description, productRequest.suggestedPrice.get(), productRequest.categories);
    }
}
