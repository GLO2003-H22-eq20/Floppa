package ulaval.glo2003.infrastructure;

import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductCategory;

import java.util.*;
import java.util.stream.Collectors;

public interface ProductRepository {
    void saveProduct(Product product);

    List<Product> findProductsBySellerId(String sellerId);

    Product findById(String id);

    List<Product> findFilteredProducts(String sellerId, String title,
                                       List<ProductCategory> categories, Float minPrice,
                                       Float maxPrice);
}
