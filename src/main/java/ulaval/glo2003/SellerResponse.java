package ulaval.glo2003;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class SellerResponse {
    private final String id;
    private final String name;
    private final Instant createdAt;
    private final String bio;
    private final List<Product> products;

    public SellerResponse(String id, String name, Instant createdAt, String bio, List<Product> products) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.bio = bio;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getBio() {
        return bio;
    }

    public List<Product> getProducts() {
        return products;
    }
}
