package ulaval.glo2003.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String sellerId;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final Offers offers;
    private final List<ProductCategory> categories;

    public Product(String sellerId, String title, String description, Float suggestedPrice, List<ProductCategory> categories) {
        this.id = UUID.randomUUID();
        this.sellerId = sellerId;
        this.createdAt = Instant.now();
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = new Offers(null, 0);
        this.categories = categories;
    }

    public String getId() {
        return id.toString();
    }

    public String getSellerId() {
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

    public Offers getOffer() {
        return offers;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }
}
