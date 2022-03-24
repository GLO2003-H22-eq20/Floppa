package ulaval.glo2003;

import io.github.cdimascio.dotenv.Dotenv;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.product.application.ProductFactory;
import ulaval.glo2003.product.application.ProductService;
import ulaval.glo2003.seller.application.SellerFactory;
import ulaval.glo2003.seller.application.SellerService;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exceptions.mappers.InvalidParameterExceptionMapper;
import ulaval.glo2003.exceptions.mappers.ItemNotFoundExceptionsMapper;
import ulaval.glo2003.exceptions.mappers.MissingParameterExceptionMapper;
import ulaval.glo2003.health.HealthResource;
import ulaval.glo2003.product.api.ProductResource;
import ulaval.glo2003.product.ProductRepository;
import ulaval.glo2003.product.api.response.ProductPresenter;
import ulaval.glo2003.seller.api.SellerResource;
import ulaval.glo2003.seller.api.response.SellerPresenter;
import ulaval.glo2003.product.persistence.ProductMongoRepository;
import ulaval.glo2003.seller.SellerRepository;
import ulaval.glo2003.seller.persistence.SellerMongoRepository;

import java.io.IOException;
import java.net.URI;

public class Main {

    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        URI uri = URI.create("http://localhost:8080/");

        DatastoreProvider datastoreProvider = new DatastoreProvider();
        SellerRepository sellerRepository = new SellerMongoRepository(datastoreProvider);
        ProductRepository productRepository = new ProductMongoRepository(datastoreProvider);
        SellerFactory sellerFactory = new SellerFactory();
        SellerService sellerService = new SellerService(sellerRepository, productRepository, sellerFactory);
        SellerPresenter sellerPresenter = new SellerPresenter();
        ProductFactory productFactory = new ProductFactory();
        ProductService productService = new ProductService(productRepository, sellerRepository, productFactory);
        ProductPresenter productPresenter = new ProductPresenter();

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(ItemNotFoundExceptionsMapper.class)
                .register(InvalidParameterExceptionMapper.class)
                .register(MissingParameterExceptionMapper.class)
                .register(new SellerResource(sellerService, sellerPresenter, uri))
                .register(new ProductResource(productService, productPresenter, uri))
                .register(HealthResource.class)
                .packages("ulaval.glo2003");

        server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }

    public static void stop() {
        server.shutdownNow();
    }
}
