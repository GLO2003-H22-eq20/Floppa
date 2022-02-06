package ulaval.glo2003.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final Instant createdAt;
    private String title;
    private String description;
    private Float suggestedPrice;
    private Offer offer;
    private List<ProductCategory> categories;

    public Product(String title, String description, Float suggestedPrice, List<ProductCategory> categories) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
    }

    public UUID getId() {
        return id;
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

    public Offer getOffer() {
        return offer;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }
}
