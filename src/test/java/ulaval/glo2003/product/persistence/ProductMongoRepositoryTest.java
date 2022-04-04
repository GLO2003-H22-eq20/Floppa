package ulaval.glo2003.product.persistence;

import dev.morphia.DeleteOptions;
import org.junit.jupiter.api.AfterEach;
import ulaval.glo2003.context.Configuration;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductRepositoryTest;

public class ProductMongoRepositoryTest extends ProductRepositoryTest {

    private DatastoreProvider datastoreProvider;

    public ProductMongoRepositoryTest() {
        datastoreProvider = new Configuration().createDatastoreProvider();
    }

    @Override
    public ProductRepository createProductRepository() {
        return new ProductMongoRepository(datastoreProvider, new ProductModelAssembler());
    }

    @AfterEach
    public void tearDown() {
        datastoreProvider.getDatastore()
                .find(ProductMongoModel.class)
                .delete( new DeleteOptions().multi(true));
    }
}
