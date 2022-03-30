package ulaval.glo2003.seller.domain;

import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Seller {
    private final UUID id;
    private final String name;
    private final String bio;
    private final LocalDate birthDate;
    private final Instant createdAt;


    public Seller(UUID id,
                  String name,
                  String bio,
                  LocalDate birthDate,
                  Instant createdAt) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Seller)) {
            return false;
        }
        Seller that = (Seller) o;
        return this.id.compareTo(that.id) == 0
                && this.createdAt.compareTo(that.createdAt) == 0
                && Objects.equals(this.bio, that.bio)
                && Objects.equals(this.name, that.name)
                && this.birthDate.equals(that.birthDate);
    }
}
