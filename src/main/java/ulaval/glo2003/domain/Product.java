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
    private Offers offers;
    private List<String> categories;
    private Seller seller;

    public Product(String title, String description, Float suggestedPrice, List<String> categories, Seller seller) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        /*ArrayList<ProductCategory> liste = new ArrayList<ProductCategory>();
        liste.add(new ProductCategory("fruit"));
        liste.add(new ProductCategory("repas"));*/
        this.categories = categories;
        this.seller = seller;
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

    public Offers getOffer() {
        return offers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Seller getSeller() {return seller;}
}
