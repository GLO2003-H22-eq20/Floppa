package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.controllers.health.HealthResource;
import ulaval.glo2003.controllers.seller.SellerResource;
import ulaval.glo2003.controllers.exceptionMappers.InvalidParameterExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.ItemNotFoundExceptionsMapper;
import ulaval.glo2003.controllers.exceptionMappers.MissingParameterExceptionMapper;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        URI uri = URI.create("http://localhost:8080/");

        SellerRepository sellerRepository = new SellerRepository();

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(ItemNotFoundExceptionsMapper.class)
                .register(InvalidParameterExceptionMapper.class)
                .register(MissingParameterExceptionMapper.class)
                .register(new SellerResource(sellerRepository, uri))
                .register(HealthResource.class)
                .packages("ulaval.glo2003");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }
}
