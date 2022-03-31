package ulaval.glo2003.seller.persistence;

import ulaval.glo2003.seller.domain.Seller;

import java.time.Instant;
import java.util.UUID;

public class SellerModelAssembler {

    public SellerMongoModel assembleEntityToMongoModel(Seller seller) {
        return new SellerMongoModel(
                UUID.fromString(seller.getId()),
                seller.getName(),
                seller.getBio(),
                seller.getBirthDate(),
                seller.getCreatedAt().toString()
        );
    }

    public Seller assembleMongoModelToEntity(SellerMongoModel sellerMongoModel) {
        return new Seller(
                sellerMongoModel.getId(),
                sellerMongoModel.getName(),
                sellerMongoModel.getBio(),
                sellerMongoModel.getBirthDate(),
                Instant.parse(sellerMongoModel.getCreatedAt())
        );
    }
}
