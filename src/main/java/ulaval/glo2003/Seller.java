package ulaval.glo2003;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Seller {
    public UUID id;
    public String name;
    public String bio;
    public Date birthDate;
    public Instant createdAt;

    public Seller(String name, String bio, Date birthDate) {
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
        this.id = UUID.randomUUID();
    }
}
