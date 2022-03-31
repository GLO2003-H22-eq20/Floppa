package ulaval.glo2003.product.persistence;

import ulaval.glo2003.product.domain.Product;

import java.util.UUID;

public class ProductModelAssembler {

    public ProductMongoModel assembleEntityToMongoModel(Product product) {
        return new ProductMongoModel(
                UUID.fromString(product.getId()),
                UUID.fromString(product.getSellerId()),
                product.getCreatedAt(),
                product.getTitle(),
                product.getDescription(),
                product.getSuggestedPrice(),
                product.getCategories());
    }

    public Product assembleMongoModelToEntity(ProductMongoModel productMongoModel) {
        return new Product(
                productMongoModel.getId(),
                productMongoModel.getSellerId().toString(),
                productMongoModel.getCreatedAt(),
                productMongoModel.getTitle(),
                productMongoModel.getDescription(),
                productMongoModel.getSuggestedPrice(),
                productMongoModel.getCategories()

        );
    }
}
