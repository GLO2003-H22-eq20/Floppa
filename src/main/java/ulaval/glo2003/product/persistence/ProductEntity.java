package ulaval.glo2003.product.persistence;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.ProductCategory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity("product")

public class ProductEntity {

    @Id
    private final ObjectId id;
    private final ObjectId sellerId;
    private final Instant createdAt;
    private final String title;
    private final String description;
    private final Float suggestedPrice;
    private final List<ObjectId> offers;
    private final List<ProductCategory> categories;


    public ProductEntity(
            ObjectId id,
            ObjectId sellerId,
            Instant createdAt,
            String title,
            String description,
            Float suggestedPrice,
            List<ProductCategory> categories
    ) {
        this.id = id;
        this.sellerId = sellerId;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = new ArrayList<ObjectId>();
        this.categories = categories;
    }



}

