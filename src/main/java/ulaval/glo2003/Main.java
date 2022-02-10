package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.controllers.health.HealthResource;
import ulaval.glo2003.controllers.product.ProductPresenter;
import ulaval.glo2003.controllers.product.ProductResource;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.controllers.seller.SellerPresenter;
import ulaval.glo2003.controllers.seller.SellerResource;
import ulaval.glo2003.controllers.exceptionMappers.InvalidParameterExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.ItemNotFoundExceptionsMapper;
import ulaval.glo2003.controllers.exceptionMappers.MissingParameterExceptionMapper;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        URI uri = URI.create("http://localhost:8080/");

        SellerRepository sellerRepository = new SellerRepository();
        ProductRepository productRepository = new ProductRepository();
        SellerPresenter sellerPresenter = new SellerPresenter();
        ProductPresenter productPresenter = new ProductPresenter();

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(ItemNotFoundExceptionsMapper.class)
                .register(InvalidParameterExceptionMapper.class)
                .register(MissingParameterExceptionMapper.class)
                .register(new SellerResource(sellerRepository, sellerPresenter, uri))
                .register(new ProductResource(sellerRepository, productPresenter, productRepository, uri))
                .register(HealthResource.class)
                .packages("ulaval.glo2003");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }
}
