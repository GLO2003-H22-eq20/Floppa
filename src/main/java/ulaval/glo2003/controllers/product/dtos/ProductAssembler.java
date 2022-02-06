package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Product;

public class ProductAssembler {
    static public Product fromRequest(ProductRequest productRequest) {
//TODO remove null
        if (productRequest.title == null) {
            throw new MissingParameterException("Missing title");
        }

        if (productRequest.description == null) {
            throw new MissingParameterException("Missing description");
        }

//        if (productRequest.suggestedPrice == null) {
//            throw new MissingParameterException("Missing suggested price");
//        }
        return new Product(productRequest.title, productRequest.description, productRequest.suggestedPrice);
    }
}
