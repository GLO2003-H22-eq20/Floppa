package ulaval.glo2003.product.persistence;

import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.product.ProductRepository;
import ulaval.glo2003.product.Product;
import ulaval.glo2003.product.ProductCategory;

import java.util.List;

public class ProductMongoRepository implements ProductRepository {
    private final DatastoreProvider datastoreProvider;

    public ProductMongoRepository(DatastoreProvider datastoreProvider) {
        this.datastoreProvider = datastoreProvider;
    }


    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public List<Product> findProductsBySellerId(String sellerId) {
        return null;
    }

    @Override
    public Product findById(String id) {
        return null;
    }

    @Override
    public List<Product> findFilteredProducts(String sellerId, String title, List<ProductCategory> categories, Float minPrice, Float maxPrice) {
        return null;
    }
}
