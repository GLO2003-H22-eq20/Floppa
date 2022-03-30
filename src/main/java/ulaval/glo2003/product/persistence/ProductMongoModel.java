package ulaval.glo2003.product.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.product.domain.ProductCategory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity("product")

public class ProductMongoModel {

    @Id
    private final UUID id;
    private final UUID sellerId;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final List<UUID> offers;
    private final List<ProductCategory> categories;

    public ProductMongoModel(
            UUID id,
            UUID sellerId,
            Instant createdAt,
            String title,
            String description,
            Float suggestedPrice,
            List<ProductCategory> categories
    ) {
        this.id = id;
        this.sellerId = sellerId;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = new ArrayList<UUID>();
        this.categories = categories;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Float getSuggestedPrice() {
        return suggestedPrice;
    }

    public List<UUID> getOffers() {
        return offers;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }
}

