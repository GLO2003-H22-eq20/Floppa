package ulaval.glo2003.domain;

import java.util.UUID;

public class Offer {
    private final UUID id;
    private final String productId;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Float amount;
    private final String message;

    public Offer(String productId, String name, String email, String phoneNumber, Float amount, String message) {
        this.id = UUID.randomUUID();
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

    public Float getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }
}
