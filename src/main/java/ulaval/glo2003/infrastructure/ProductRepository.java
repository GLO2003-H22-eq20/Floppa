package ulaval.glo2003.infrastructure;

import ulaval.glo2003.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {
    private final Map<String, Product> productMap = Collections.synchronizedMap(new HashMap<>());

    public void saveProduct(Product product) {
        productMap.put(product.getId().toString(), product);
    }

    public List<Product> findProductsBySellerId(String sellerId) {
        return productMap.values()
                .stream()
                .filter(product -> Objects.equals(product.getSellerId(), sellerId))
                .collect(Collectors.toList());
    }
}
