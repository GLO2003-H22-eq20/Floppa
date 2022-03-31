package ulaval.glo2003.offer.persistence;

import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OfferMongoRepository implements OfferRepository {

    private final DatastoreProvider datastoreProvider;
    private final OfferModelAssembler offerModelAssembler;

    public OfferMongoRepository(DatastoreProvider datastoreProvider, OfferModelAssembler offerModelAssembler) {
        this.datastoreProvider = datastoreProvider;
        this.offerModelAssembler = offerModelAssembler;
    }

    @Override
    public void saveOffer(Offer offer) {
        OfferMongoModel offerMongoModel = offerModelAssembler.assembleEntityToMongoModel(offer);
        datastoreProvider.getDatastore().save(offerMongoModel);
    }

    @Override
    public List<Offer> getOffersBy(String productId) {
        Query<OfferMongoModel> offerEntityQuery = datastoreProvider.getDatastore().find(OfferMongoModel.class);
        offerEntityQuery.filter(Filters.eq("productId", productId));

        return offerEntityQuery.stream()
                .map(offerModelAssembler::assembleMongoModelToEntity)
                .collect(Collectors.toList());
    }
}
