package ulaval.glo2003.controllers.product.dtos;

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
    private final ProductSellerResponse seller;
    private final List<String> categories;

    public ProductResponse(UUID id, Instant createdAt, String title, String description, Float suggestedPrice, Offers offers, ProductSellerResponse seller, List<String> categories) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = offers;
        this.seller = seller;
        this.categories = categories;
    }

    public UUID getId() {return id;}

    public Instant getCreatedAt() {return createdAt;}

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public Float getSuggestedPrice() {return suggestedPrice;}

    public Offers getOffers() {
        return offers;
    }

    public ProductSellerResponse getSeller() {return seller;}

    public List<String> getCategories() {return categories;}
}
