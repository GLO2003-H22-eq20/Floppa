package ulaval.glo2003;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class SellerAssembler {
    static public Seller fromRequest(SellerRequest sellerRequest) {
        Date minDate = Date.from(LocalDateTime.now().minusYears(18).toInstant(ZoneOffset.UTC));
        if (sellerRequest.birthDate.after(minDate)) {
            throw new InvalidParameterException("Seller must be at least 18 years old");
        }

        return new Seller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);
    }

    static public SellerResponse toResponse(Seller seller) {
        return new SellerResponse(seller.id.toString(), seller.name, seller.createdAt, seller.bio, seller.products);
    }
}
