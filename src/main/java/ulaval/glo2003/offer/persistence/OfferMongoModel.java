package ulaval.glo2003.offer.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.time.Instant;
import java.util.UUID;

@Entity("offer")
public class OfferMongoModel {
    @Id
    private UUID id;
    private String productId;
    private String name;
    private String email;
    private String phoneNumber;
    private Double amount;
    private String message;
    private Instant createdAt;

    public OfferMongoModel() {
    }

    public OfferMongoModel(
            UUID id,
            String productId,
            String name,
            String email,
            String phoneNumber,
            Double amount,
            String message,
            Instant createdAt
    ) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getProductId() {
        return productId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
