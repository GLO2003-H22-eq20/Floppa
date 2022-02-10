package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.util.UUID;

public class ProductAssembler {
    static public Product fromRequest(ProductRequest productRequest, Seller seller) {
    //TODO remove null
        if (productRequest.title == null) {
            throw new MissingParameterException("Missing title");
        }

        if (productRequest.description == null) {
            throw new MissingParameterException("Missing description");
        }

    //TODO add MISSING_PARAMETER_EXCEPTION

        return new Product(productRequest.title, productRequest.description, productRequest.suggestedPrice, productRequest.categories, seller);
    }
}
