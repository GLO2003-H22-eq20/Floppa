package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.offer.domain.OfferFactory;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.offer.domain.OffersAssembler;
import ulaval.glo2003.offer.persistence.OfferModelAssembler;
import ulaval.glo2003.offer.persistence.OfferMongoRepository;
import ulaval.glo2003.offer.service.OfferService;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exceptions.mappers.DefaultExceptionMapper;
import ulaval.glo2003.exceptions.mappers.InvalidParameterExceptionMapper;
import ulaval.glo2003.exceptions.mappers.ItemNotFoundExceptionMapper;
import ulaval.glo2003.exceptions.mappers.MissingParameterExceptionMapper;
import ulaval.glo2003.health.HealthResource;
import ulaval.glo2003.offer.persistence.OfferInMemoryRepository;
import ulaval.glo2003.product.domain.ProductFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.persistence.ProductModelAssembler;
import ulaval.glo2003.product.persistence.ProductMongoRepository;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.product.ui.ProductResource;
import ulaval.glo2003.product.ui.response.ProductResponseAssembler;
import ulaval.glo2003.seller.domain.SellerFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.SellerService;
import ulaval.glo2003.seller.persistence.SellerModelAssembler;
import ulaval.glo2003.seller.persistence.SellerMongoRepository;
import ulaval.glo2003.seller.ui.SellerResource;
import ulaval.glo2003.seller.ui.response.SellerResponseAssembler;


import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        final String port = System.getenv("PORT");
        final String baseUri = "http://0.0.0.0:" + port;
        URI uri = URI.create(baseUri);

        DatastoreProvider datastoreProvider = new DatastoreProvider();

        SellerModelAssembler sellerModelAssembler = new SellerModelAssembler();
        ProductModelAssembler productModelAssembler = new ProductModelAssembler();
        OfferModelAssembler offerModelAssembler = new OfferModelAssembler();
        OffersAssembler offersAssembler = new OffersAssembler();

        SellerRepository sellerRepository = new SellerMongoRepository(datastoreProvider, sellerModelAssembler);
        ProductRepository productRepository = new ProductMongoRepository(datastoreProvider, productModelAssembler);
        OfferRepository offerRepository = new OfferMongoRepository(datastoreProvider, offerModelAssembler);

        SellerFactory sellerFactory = new SellerFactory();
        ProductFactory productFactory = new ProductFactory();
        OfferFactory offerFactory = new OfferFactory();

        SellerResponseAssembler sellerResponseAssembler = new SellerResponseAssembler();
        ProductResponseAssembler productResponseAssembler = new ProductResponseAssembler();


        SellerService sellerService = new SellerService(
                sellerRepository,
                productRepository,
                offerRepository,
                sellerFactory,
                offersAssembler);
        ProductService productService = new ProductService(
                productRepository,
                sellerRepository,
                offerRepository,
                productFactory,
                offersAssembler);
        OfferService offerService = new OfferService(offerRepository, productRepository, offerFactory);


        LoggingFeature loggingFeature = new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000);

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(loggingFeature)
                .register(ItemNotFoundExceptionMapper.class)
                .register(InvalidParameterExceptionMapper.class)
                .register(MissingParameterExceptionMapper.class)
                .register(DefaultExceptionMapper.class)
                .register(new SellerResource(sellerService, sellerResponseAssembler, uri))
                .register(new ProductResource(productService,offerService,productResponseAssembler,uri))
                .register(HealthResource.class)
                .packages("ulaval.glo2003");

        server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }

    public static void stop() {
        server.shutdownNow();
    }
}
