package ulaval.glo2003.product.persistence;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public class ProductModelAssembler {

    public ProductMongoModel assembleEntityToMongoModel(Product product) {
        return new ProductMongoModel(
                UUID.fromString(product.getId()),
                product.getSellerId(),
                product.getCreatedAt().toString(),
                product.getTitle(),
                product.getDescription(),
                product.getSuggestedPrice(),
                product.getCategories(),
                product.getViewsCount()
        );
    }

    public Product assembleMongoModelToEntity(ProductMongoModel productMongoModel) {
        return new Product(
                productMongoModel.getId(),
                productMongoModel.getSellerId(),
                Instant.parse(productMongoModel.getCreatedAt()),
                productMongoModel.getTitle(),
                productMongoModel.getDescription(),
                productMongoModel.getSuggestedPrice(),
                productMongoModel.getCategories(),
                productMongoModel.getViewsCount()
        );
    }
}
