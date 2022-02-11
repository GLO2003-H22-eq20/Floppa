package ulaval.glo2003.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Seller {
    private final UUID id;
    private final String name;
    private final String bio;
    private final LocalDate birthDate;
    private final Instant createdAt;
    private final List<Product> products;

    public Seller(String name, String bio, LocalDate birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }
}
