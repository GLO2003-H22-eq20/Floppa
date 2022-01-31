package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Seller;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class SellerAssembler {
    static public Seller fromRequest(SellerRequest sellerRequest) {
        if(sellerRequest.name == null) {
            throw new MissingParameterException("Missing Name");
        }

        if(sellerRequest.bio == null) {
            throw new MissingParameterException("Missing Bio");
        }

        if(sellerRequest.birthDate == null) {
            throw new MissingParameterException("Missing BirthDate");
        }

        Date minDate = Date.from(LocalDateTime.now().minusYears(18).toInstant(ZoneOffset.UTC));
        if (sellerRequest.birthDate.after(minDate)) {
            throw new InvalidParameterException("Seller must be at least 18 years old");
        }

        return new Seller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);
    }

    static public SellerResponse toResponse(Seller seller) {
        return new SellerResponse(seller.getId().toString(), seller.getName(), seller.getCreatedAt().toString(), seller.getBio(), seller.getProducts());
    }
}
