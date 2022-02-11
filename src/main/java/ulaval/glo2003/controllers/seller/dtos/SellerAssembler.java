package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.controllers.exceptions.InvalidParameterException;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.domain.Seller;

import java.time.LocalDate;

public class SellerAssembler {
    static public Seller fromRequest(SellerRequest sellerRequest) {
        if (sellerRequest.name == null) {
            throw new MissingParameterException("Missing Name");
        }
        if (sellerRequest.bio == null) {
            throw new MissingParameterException("Missing Bio");
        }
        if (sellerRequest.birthDate == null) {
            throw new MissingParameterException("Missing BirthDate");
        }
        if (sellerRequest.name.isEmpty()) {
            throw new InvalidParameterException("Seller's name is invalid");
        }
        if (sellerRequest.bio.isEmpty()) {
            throw new InvalidParameterException("Seller's bio is invalid");
        }
        if (sellerRequest.birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidParameterException("Seller must be at least 18 years old");
        }
        return new Seller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);
    }
}
