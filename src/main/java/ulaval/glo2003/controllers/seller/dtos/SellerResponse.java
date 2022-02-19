package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.controllers.product.dtos.ProductResponse;

import java.util.List;

public class SellerResponse {
    private final String id;
    private final String name;
    private final String createdAt;
    private final String bio;
    private final List<ProductResponse> products;

    public SellerResponse(String id, String name, String createdAt, String bio, List<ProductResponse> products) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.bio = bio;
        this.products = products;
    }
    public SellerResponse(String id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = null;
        this.bio = null;
        this.products = null;
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

    public List<ProductResponse> getProducts() {
        return products;
    }
}
