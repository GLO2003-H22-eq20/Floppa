package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Offers;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class ProductResponse {
    private final UUID id;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final Offers offers;
    private final List<String> categories;
    private SellerResponse seller;

    public ProductResponse(UUID id, Instant createdAt, String title, String description, Float suggestedPrice,
                           Offers offers, List<String> categories, SellerResponse seller) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = offers;
        this.categories = categories;
        this.seller = seller;
    }

    public ProductResponse(UUID id, Instant createdAt, String title, String description,
                           Float suggestedPrice, Offers offers, List<String> categories) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = offers;
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

    public Offers getOffers() {
        return offers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public SellerResponse getSeller() {
        return seller;
    }
}
