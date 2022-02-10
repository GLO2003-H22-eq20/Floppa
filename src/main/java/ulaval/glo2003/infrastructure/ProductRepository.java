package ulaval.glo2003.infrastructure;

import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<String, Product> productMap = Collections.synchronizedMap(new HashMap<>());

    public void saveProduct(Product product) {
        productMap.put(product.getId().toString(), product);
    }

    public Product findById(String id) {
        if (productMap.get(id) == null) {
            throw new ItemNotFoundException();
        }
        return productMap.get(id);
    }
}
