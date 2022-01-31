package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.domain.Offer;

import java.time.Instant;
import java.util.UUID;

public class ProductResponse {

    private final UUID id;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final OfferResponse offer;

    public ProductResponse(UUID id, Instant createdAt, String title, String description, Float suggestedPrice, OfferResponse offer) {
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

    public OfferResponse getOffer() {return offer;}
}
