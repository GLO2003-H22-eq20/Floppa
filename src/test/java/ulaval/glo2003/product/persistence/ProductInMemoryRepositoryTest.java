package ulaval.glo2003.product.persistence;

import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductRepositoryTest;

public class ProductInMemoryRepositoryTest extends ProductRepositoryTest {
    @Override
    protected ProductRepository createProductRepository() {
        return new ProductInMemoryRepository();
    }
}
