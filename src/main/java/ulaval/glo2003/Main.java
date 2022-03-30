package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.application.offer.OfferFactory;
import ulaval.glo2003.application.offer.OfferService;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exceptions.mappers.DefaultExceptionMapper;
import ulaval.glo2003.exceptions.mappers.InvalidParameterExceptionMapper;
import ulaval.glo2003.exceptions.mappers.ItemNotFoundExceptionMapper;
import ulaval.glo2003.exceptions.mappers.MissingParameterExceptionMapper;
import ulaval.glo2003.health.HealthResource;
import ulaval.glo2003.infrastructure.OfferRepository;
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
        URI uri = URI.create("http://localhost:8080/");

        DatastoreProvider datastoreProvider = new DatastoreProvider();

        SellerModelAssembler sellerModelAssembler = new SellerModelAssembler();
        ProductModelAssembler productModelAssembler = new ProductModelAssembler();

        SellerRepository sellerRepository = new SellerMongoRepository(datastoreProvider, sellerModelAssembler);
        ProductRepository productRepository = new ProductMongoRepository(datastoreProvider, productModelAssembler);
        OfferRepository offerRepository = new OfferRepository();

        SellerFactory sellerFactory = new SellerFactory();
        ProductFactory productFactory = new ProductFactory();
        OfferFactory offerFactory = new OfferFactory();

        SellerResponseAssembler sellerResponseAssembler = new SellerResponseAssembler();
        ProductResponseAssembler productResponseAssembler = new ProductResponseAssembler();

        SellerService sellerService = new SellerService(
                sellerRepository,
                productRepository,
                offerRepository,
                sellerFactory);
        ProductService productService = new ProductService(
                productRepository,
                sellerRepository,
                offerRepository,
                productFactory
        );
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
