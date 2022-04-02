package ulaval.glo2003.seller.ui.response;

import ulaval.glo2003.product.ui.response.ProductResponse;

import java.util.List;

public class SellerResponse {
    private String id;
    private String name;
    private String createdAt;
    private String bio;
    private String birthDate;
    private List<ProductResponse> products;

    public SellerResponse() {
    }

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
    }

    public SellerResponse(String id, String name, String createdAt, String bio, String birthDate, List<ProductResponse> products) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.bio = bio;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }
}