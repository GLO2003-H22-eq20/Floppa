package ulaval.glo2003.offer.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.UUID;

@Entity("offer")
public class OfferMongoModel {
    @Id
    private final UUID id;
    private final String productId;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Double amount;
    private final String message;

    public OfferMongoModel(UUID id, String productId, String name, String email, String phoneNumber, Double amount, String message) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.message = message;
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
}
