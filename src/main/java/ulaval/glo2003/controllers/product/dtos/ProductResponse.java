package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.domain.Offers;

import java.time.Instant;
import java.util.UUID;

public class ProductResponse {
    private final UUID id;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final Offers offers;

    public ProductResponse(UUID id,
                           Instant createdAt,
                           String title,
                           String description,
                           Float suggestedPrice,
                           Offers offers) {
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
