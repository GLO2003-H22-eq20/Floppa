package ulaval.glo2003.controllers.seller.dtos;

import ulaval.glo2003.application.seller.dtos.SellerDto;

public class SellerPresenter {
    public SellerResponse presentSeller(SellerDto sellerDto) {
        return new SellerResponse(sellerDto.getId(), sellerDto.getName(), sellerDto.getCreatedAt(), sellerDto.getBio(), sellerDto.getProducts());
    }
}
