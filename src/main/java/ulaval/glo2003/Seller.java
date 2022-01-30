package ulaval.glo2003;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Seller {
    private final UUID id;
    private final String name;
    private final String bio;
    private final Date birthDate;
    private final Instant createdAt;
    private List<Product> products;

    public Seller(String name, String bio, Date birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
        this.products = new ArrayList<>();
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

    public Date getBirthDate() {
        return birthDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }
}
