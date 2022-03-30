package ulaval.glo2003.product.persistence;

import ulaval.glo2003.exceptions.ItemNotFoundException;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;

import java.util.*;
import java.util.stream.Collectors;

public class ProductInMemoryRepository implements ProductRepository {
    private final Map<String, Product> productMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void saveProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public List<Product> findProductsBySellerId(String sellerId) {
        return productMap.values()
                .stream()
                .filter(product -> Objects.equals(product.getSellerId(), sellerId))
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(String id) {
        if (productMap.get(id) == null) {
            throw new ItemNotFoundException();
        }
        return productMap.get(id);
    }

    @Override
    public List<Product> findFilteredProducts(String sellerId, String title,
                                              List<ProductCategory> categories, Double minPrice,
                                              Double maxPrice) {
        List<Product> products = new ArrayList<>(productMap.values());
        if (Optional.ofNullable(sellerId).isPresent()) {
            products = filterBySellerId(sellerId, products);
        }
        if (Optional.ofNullable(title).isPresent()) {
            products = filterByTitle(title, products);
        }
        if (!categories.isEmpty()) {
            products = filterByCategories(categories, products);
        }
        if (Optional.ofNullable(minPrice).isPresent()) {
            products = filterByMinPrice(minPrice, products);
        }
        if (Optional.ofNullable(maxPrice).isPresent()) {
            products = filterByMaxPrice(maxPrice, products);
        }
        return products;
    }

    private List<Product> filterBySellerId(String sellerId, List<Product> products) {
        return products.stream()
                .filter(product -> Objects.equals(product.getSellerId(), sellerId))
                .collect(Collectors.toList());
    }

    private List<Product> filterByTitle(String title, List<Product> products) {
        return products.stream()
                .filter(product -> product.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    private List<Product> filterByCategories(List<ProductCategory> categories, List<Product> products) {
        return products.stream()
                .filter(product -> categories.stream()
                        .anyMatch(category -> product.getCategories().contains(category)))
                .collect(Collectors.toList());
    }

    private List<Product> filterByMinPrice(Double minPrice, List<Product> products) {
        return products.stream()
                .filter(product -> product.getSuggestedPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    private List<Product> filterByMaxPrice(Double maxPrice, List<Product> products) {
        return products.stream()
                .filter(product -> product.getSuggestedPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
