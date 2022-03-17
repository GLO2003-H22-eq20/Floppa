package ulaval.glo2003.infrastructure;

import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filter;
import org.bson.types.ObjectId;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;
import ulaval.glo2003.controllers.seller.dtos.SellerEntity;
import ulaval.glo2003.domain.Seller;

import java.util.UUID;

public class SellerMongoRepository implements SellerRepository{
    private final DatastoreProvider datastoreProvider;

    public SellerMongoRepository(DatastoreProvider datastoreProvider) {
        this.datastoreProvider = datastoreProvider;
    }

    @Override
    public void saveSeller(Seller seller) {
        SellerEntity sellerEntity = new SellerEntity(new ObjectId(), seller.getName(), seller.getBio(), seller.getBirthDate(), seller.getCreatedAt());
        datastoreProvider.getDatastore().save(sellerEntity);
    }

    @Override
    public Seller findById(String id) {

        Query<SellerEntity> sellerEntityQuery = datastoreProvider.getDatastore().find(SellerEntity.class);
        ObjectId objectId = new ObjectId(id);
        Filter filter = (Filter) Filters.eq("seller.id", objectId);
        sellerEntityQuery.filter(filter);
        SellerEntity sellerEntity = sellerEntityQuery.first();
        if (sellerEntity == null){
            throw new ItemNotFoundException();
        }
        return new Seller(sellerEntity.getId().toString(), sellerEntity.getName(), sellerEntity.getBio(), sellerEntity.getBirthDate(), sellerEntity.getCreatedAt());
    }
}
