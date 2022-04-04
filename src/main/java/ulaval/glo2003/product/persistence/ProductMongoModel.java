package ulaval.glo2003.product.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.product.domain.ProductCategory;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity("product")

public class ProductMongoModel {
    @Id
    private UUID id;
    private String sellerId;
    private String createdAt;
    private String title;
    private String description;
    private Double suggestedPrice;
    private List<ProductCategory> categories;

    public ProductMongoModel() {
    }

    public ProductMongoModel(
            UUID id,
            String sellerId,
            String createdAt,
            String title,
            String description,
            Double suggestedPrice,
            List<ProductCategory> categories
    ) {
        this.id = id;
        this.sellerId = sellerId;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
    }

    public UUID getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }
}

