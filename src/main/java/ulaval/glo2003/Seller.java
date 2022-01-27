package ulaval.glo2003;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Seller {
    public UUID id;
    public String name;
    public String bio;
    public Date birthDate;
    public Instant createdAt;
    public List<Product> products = new ArrayList<>();

    public Seller(String name, String bio, Date birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = Instant.now();
    }
}
