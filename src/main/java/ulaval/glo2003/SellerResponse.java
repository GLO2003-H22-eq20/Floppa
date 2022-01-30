package ulaval.glo2003;

import java.util.List;

public class SellerResponse {
    private final String id;
    private final String name;
    private final String createdAt;
    private final String bio;
    private final List<Product> products;

    public SellerResponse(String id, String name, String createdAt, String bio, List<Product> products) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getBio() {
        return bio;
    }

    public List<Product> getProducts() {
        return products;
    }
}
