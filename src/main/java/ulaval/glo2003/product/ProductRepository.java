package ulaval.glo2003.product;

import java.util.*;

public interface ProductRepository {
    void saveProduct(Product product);

    List<Product> findProductsBySellerId(String sellerId);

    Product findById(String id);

    List<Product> findFilteredProducts(String sellerId, String title,
                                       List<ProductCategory> categories, Float minPrice,
                                       Float maxPrice);
}
