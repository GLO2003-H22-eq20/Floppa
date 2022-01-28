package ulaval.glo2003;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Seller {
    private UUID id;
    private String name;
    private String bio;
    private Date birthDate;
    private Instant createdAt;
    private List<Product> products = new ArrayList<>();

    public Seller(String name, String bio, Date birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
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
