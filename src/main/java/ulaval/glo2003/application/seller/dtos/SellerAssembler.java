package ulaval.glo2003.application.seller.dtos;

import ulaval.glo2003.domain.Seller;

public class SellerAssembler {
    public SellerDto toDto(Seller seller) {
        return new SellerDto(seller.getId().toString(), seller.getName(), seller.getCreatedAt().toString(), seller.getBio(), seller.getProducts());
    }
}
