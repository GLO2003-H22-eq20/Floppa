package ulaval.glo2003.seller.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity("seller")

public class SellerMongoModel {
    @Id
    private final UUID id;
    private final String name;
    private final String bio;
    private final LocalDate birthDate;
    private final String createdAt;

    public SellerMongoModel(UUID id, String name, String bio, LocalDate birthDate, String createdAt) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
