package ulaval.glo2003;

import java.time.Instant;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final Instant createdAt;
    private String title;
    private String description;
    private Float suggestedPrice;
    private Offers offers;

    public Product(UUID id, Instant createdAt, String title, String description, Float suggestedPrice, Offers offers) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = offers;
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

    public Offers getOffers() {
        return offers;
    }
}
