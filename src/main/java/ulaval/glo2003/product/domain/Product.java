package ulaval.glo2003.product.domain;


import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String sellerId;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final List<ProductCategory> categories;

    public Product(UUID id,
            String sellerId,
            Instant createdAt,
            String title,
            String description,
            Double suggestedPrice,
            List<ProductCategory> categories) {
        this.id = id;
        this.sellerId = sellerId;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
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

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product that = (Product) o;

        return this.id.compareTo(that.id) == 0
                && this.createdAt.compareTo(that.createdAt) == 0
                && this.sellerId.equals(that.sellerId)
                && this.title.equals(that.title)
                && this.description.equals(that.description)
                && this.suggestedPrice.equals(that.suggestedPrice)
                && this.categories.containsAll(that.categories);
    }
}
