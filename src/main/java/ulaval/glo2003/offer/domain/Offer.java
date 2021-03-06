package ulaval.glo2003.offer.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Offer {
    private final UUID id;
    private final String productId;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Double amount;
    private final String message;
    private final Instant createdAt;

    public Offer(UUID id,
                 String productId,
                 String name, String email,
                 String phoneNumber,
                 Double amount,
                 String message,
                 Instant createdAt) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        Offer that = (Offer) o;
        return this.id.compareTo(that.id) == 0
                && Objects.equals(this.productId, that.productId)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.email, that.email)
                && Objects.equals(this.phoneNumber, that.phoneNumber)
                && Objects.equals(this.amount, that.amount)
                && Objects.equals(this.message, that.message)
                && Objects.equals(this.createdAt, that.createdAt);
    }
}
