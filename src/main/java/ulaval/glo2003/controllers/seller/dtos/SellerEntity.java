package ulaval.glo2003.controllers.seller.dtos;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexes;
import org.bson.types.ObjectId;

@Entity("seller")


public class SellerEntity {
    @Id
    private final ObjectId id;
    private final String name;
    private final String bio;

    public SellerEntity(ObjectId id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }
}
