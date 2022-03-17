package ulaval.glo2003.domain;

import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class Seller {
    private final UUID id;
    private final String name;
    private final String bio;
    private final LocalDate birthDate;
    private final Instant createdAt;

    public Seller(String name, String bio, LocalDate birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
    }

    public Seller(String id, String name, String bio, LocalDate birthDate, Instant createdAt){
        this.id = UUID.fromString(id);
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id.toString();
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
