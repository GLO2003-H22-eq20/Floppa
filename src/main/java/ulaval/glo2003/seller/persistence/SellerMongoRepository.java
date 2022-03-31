package ulaval.glo2003.seller.persistence;

import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exceptions.ItemNotFoundException;
import ulaval.glo2003.exceptions.MissingParameterException;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.Seller;

import java.util.UUID;

public class SellerMongoRepository implements SellerRepository {
    private final DatastoreProvider datastoreProvider;
    private final SellerModelAssembler sellerModelAssembler;

    public SellerMongoRepository(DatastoreProvider datastoreProvider, SellerModelAssembler sellerModelAssembler) {
        this.datastoreProvider = datastoreProvider;
        this.sellerModelAssembler = sellerModelAssembler;
    }

    @Override
    public void saveSeller(Seller seller) {
        SellerMongoModel sellerMongoModel = sellerModelAssembler.assembleEntityToMongoModel(seller);
        datastoreProvider.getDatastore().save(sellerMongoModel);
    }

    @Override
    public Seller findById(String id) {
        Query<SellerMongoModel> sellerEntityQuery = datastoreProvider.getDatastore().find(SellerMongoModel.class);
        try {
            sellerEntityQuery.filter(Filters.eq("_id", UUID.fromString(id)));
        }catch (Exception e){
            throw new ItemNotFoundException();
        }
        SellerMongoModel sellerMongoModel = sellerEntityQuery.first();
        if (sellerMongoModel == null) {
            throw new ItemNotFoundException();
        }
        return sellerModelAssembler.assembleMongoModelToEntity(sellerMongoModel);
    }
}
