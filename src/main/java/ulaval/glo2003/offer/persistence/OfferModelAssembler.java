package ulaval.glo2003.offer.persistence;

import ulaval.glo2003.offer.domain.Offer;

public class OfferModelAssembler {
    public OfferMongoModel assembleEntityToMongoModel(Offer offer) {
        return new OfferMongoModel(
                offer.getId(),
                offer.getProductId(),
                offer.getName(),
                offer.getEmail(),
                offer.getPhoneNumber(),
                offer.getAmount(),
                offer.getMessage());
    }

    public Offer assembleMongoModelToEntity(OfferMongoModel offerMongoModel) {
        return new Offer(
                offerMongoModel.getId(),
                offerMongoModel.getProductId(),
                offerMongoModel.getName(),
                offerMongoModel.getEmail(),
                offerMongoModel.getPhoneNumber(),
                offerMongoModel.getAmount(),
                offerMongoModel.getMessage()
        );
    }
}
