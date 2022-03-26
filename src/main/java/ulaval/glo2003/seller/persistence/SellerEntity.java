package ulaval.glo2003.seller.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexes;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity("seller")

public class SellerEntity {
    @Id
    private final UUID id;
    private final String name;
    private final String bio;
    private final LocalDate birthDate;
    private final Instant createdAt;

    public SellerEntity(UUID id, String name, String bio, LocalDate birthDate, Instant createdAt) {
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

    public Instant getCreatedAt() {
        return createdAt;
    }
}
