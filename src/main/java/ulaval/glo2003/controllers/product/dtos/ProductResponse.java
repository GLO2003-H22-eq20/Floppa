package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.OfferResponse;
import ulaval.glo2003.domain.Offer;

import java.time.Instant;
import java.util.UUID;

public class ProductResponse {

    private final UUID id;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final Offer offer;

    public ProductResponse(UUID id, Instant createdAt, String title, String description, Float suggestedPrice, Offer offer) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offer = offer;
    }

    public UUID getId() {return id;}

    public Instant getCreatedAt() {return createdAt;}

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public Float getSuggestedPrice() {return suggestedPrice;}

    public Offer getOffer() {
        return offer;
    }
}
