package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.application.offer.OfferFactory;
import ulaval.glo2003.application.offer.OfferService;
import ulaval.glo2003.application.product.ProductFactory;
import ulaval.glo2003.application.product.ProductService;
import ulaval.glo2003.application.seller.SellerFactory;
import ulaval.glo2003.application.seller.SellerService;
import ulaval.glo2003.controllers.exception.InvalidParameterExceptionMapper;
import ulaval.glo2003.controllers.exception.ItemNotFoundExceptionsMapper;
import ulaval.glo2003.controllers.exception.MissingParameterExceptionMapper;
import ulaval.glo2003.controllers.health.HealthResource;
import ulaval.glo2003.controllers.product.ProductResource;
import ulaval.glo2003.controllers.product.dtos.ProductPresenter;
import ulaval.glo2003.controllers.seller.SellerResource;
import ulaval.glo2003.controllers.seller.dtos.SellerPresenter;
import ulaval.glo2003.infrastructure.OfferRepository;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.io.IOException;
import java.net.URI;

public class Main {

    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        URI uri = URI.create("http://localhost:8080/");

        SellerRepository sellerRepository = new SellerRepository();
        ProductRepository productRepository = new ProductRepository();
        OfferRepository offerRepository = new OfferRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerService sellerService = new SellerService(sellerRepository, productRepository, offerRepository,
                sellerFactory);
        SellerPresenter sellerPresenter = new SellerPresenter();
        ProductFactory productFactory = new ProductFactory();
        ProductService productService = new ProductService(productRepository, sellerRepository, offerRepository,
                productFactory);
        ProductPresenter productPresenter = new ProductPresenter();
        OfferFactory offerFactory = new OfferFactory();
        OfferService offerService = new OfferService(offerRepository, productRepository, offerFactory);


        ResourceConfig resourceConfig = new ResourceConfig()
                .register(ItemNotFoundExceptionsMapper.class)
                .register(InvalidParameterExceptionMapper.class)
                .register(MissingParameterExceptionMapper.class)
                .register(new SellerResource(sellerService, sellerPresenter, uri))
                .register(new ProductResource(productService, offerService, productPresenter, uri))
                .register(HealthResource.class)
                .packages("ulaval.glo2003");

        server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }

    public static void stop() {
        server.shutdownNow();
    }
}
