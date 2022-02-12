package ulaval.glo2003.application.seller;

import ulaval.glo2003.application.seller.dtos.SellerAssembler;
import ulaval.glo2003.application.seller.dtos.SellerDto;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.infrastructure.SellerRepository;

public class SellerService {
    private final SellerRepository sellerRepository;
    private final SellerFactory sellerFactory;
    private final SellerAssembler sellerAssembler;

    public SellerService(SellerRepository sellerRepository,
                         SellerFactory sellerFactory,
                         SellerAssembler sellerAssembler) {
        this.sellerRepository = sellerRepository;
        this.sellerFactory = sellerFactory;
        this.sellerAssembler = sellerAssembler;
    }

    public String createSeller(SellerRequest sellerRequest) {
        Seller seller = sellerFactory.createSeller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);

        sellerRepository.saveSeller(seller);

        return seller.getId().toString();
    }

    public SellerDto getSeller(String id) {
        Seller seller = sellerRepository.findById(id);

        return sellerAssembler.toDto(seller);
    }
}
