package ulaval.glo2003.product.persistence;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exceptions.ItemNotFoundException;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductMongoRepository implements ProductRepository {
    private final DatastoreProvider datastoreProvider;
    private final ProductModelAssembler productModelAssembler;

    public ProductMongoRepository(DatastoreProvider datastoreProvider, ProductModelAssembler productModelAssembler) {
        this.datastoreProvider = datastoreProvider;
        this.productModelAssembler = productModelAssembler;
    }

    @Override
    public void save(Product product) {
        ProductMongoModel productMongoModel = productModelAssembler.assembleEntityToMongoModel(product);
        datastoreProvider.getDatastore().save(productMongoModel);
    }

    @Override
    public List<Product> findProductsBySellerId(String sellerId) {
        Query<ProductMongoModel> productEntityQuery = datastoreProvider.getDatastore().find(ProductMongoModel.class);
        productEntityQuery.filter(Filters.eq("sellerId", sellerId));

        return productEntityQuery.stream().map(productModelAssembler::assembleMongoModelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(String id) {
        Query<ProductMongoModel> productEntityQuery = datastoreProvider.getDatastore().find(ProductMongoModel.class);
        try {
            productEntityQuery.filter(Filters.eq("_id", UUID.fromString(id)));
        } catch (Exception e) {
            throw new ItemNotFoundException();
        }
        ProductMongoModel productMongoModel = productEntityQuery.first();
        if (productMongoModel == null) {
            throw new ItemNotFoundException();
        }
        return productModelAssembler.assembleMongoModelToEntity(productMongoModel);
    }

    @Override
    public List<Product> findFilteredProducts(
            String sellerId,
            String title,
            List<ProductCategory> categories,
            Double minPrice,
            Double maxPrice
    ) {
        Query<ProductMongoModel> productEntityQuery = datastoreProvider.getDatastore().find(ProductMongoModel.class);

        if (Optional.ofNullable(sellerId).isPresent()) {
            productEntityQuery.filter(Filters.eq("sellerId", sellerId));
        }
        if (Optional.ofNullable(title).isPresent()) {
            productEntityQuery.filter(Filters.regex("title").caseInsensitive().pattern(title));
        }
        if (!categories.isEmpty()) {
            productEntityQuery.filter(Filters.in("categories", categories));
        }
        if (Optional.ofNullable(minPrice).isPresent()) {
            productEntityQuery.filter(Filters.gte("suggestedPrice", minPrice));
        }
        if (Optional.ofNullable(maxPrice).isPresent()) {
            productEntityQuery.filter(Filters.lte("suggestedPrice", maxPrice));
        }
        return productEntityQuery.stream().map(productModelAssembler::assembleMongoModelToEntity)
                .collect(Collectors.toList());

    }

}
