package ulaval.glo2003.application.seller;

import org.bson.types.ObjectId;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.controllers.seller.dtos.SellerEntity;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.valueObject.SellerProducts;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.util.List;
import java.util.UUID;

public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final SellerFactory sellerFactory;
    private final DatastoreProvider datastoreProvider;

    public SellerService(SellerRepository sellerRepository,
            ProductRepository productRepository,
            SellerFactory sellerFactory,
            DatastoreProvider datastoreProvider) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.sellerFactory = sellerFactory;
        this.datastoreProvider = datastoreProvider;
    }

    public String createSeller(SellerRequest sellerRequest) {
        Seller seller = sellerFactory.createSeller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);


        SellerEntity sellerEntity = new SellerEntity(new ObjectId(), "test", "je suis un test", seller.getBirthDate(), seller.getCreatedAt());

        this.datastoreProvider.getDatastore().save(sellerEntity);

        sellerRepository.saveSeller(seller);

        return seller.getId();
    }

    public SellerProducts getSeller(String id) {
        Seller seller = sellerRepository.findById(id);
        List<Product> products = productRepository.findProductsBySellerId(id);

        return new SellerProducts(seller, products);
    }
}
