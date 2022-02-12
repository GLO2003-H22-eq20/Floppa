package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.controllers.exceptionMappers.InvalidParameterExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.ItemNotFoundExceptionsMapper;
import ulaval.glo2003.controllers.exceptionMappers.MissingParameterExceptionMapper;
import ulaval.glo2003.controllers.health.HealthResource;
import ulaval.glo2003.controllers.product.ProductResource;
import ulaval.glo2003.controllers.seller.SellerResource;
import ulaval.glo2003.controllers.seller.dtos.SellerPresenter;
import ulaval.glo2003.application.product.ProductFactory;
import ulaval.glo2003.application.seller.SellerFactory;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;
import ulaval.glo2003.application.product.ProductService;
import ulaval.glo2003.application.seller.SellerService;
import ulaval.glo2003.application.seller.dtos.SellerAssembler;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        URI uri = URI.create("http://localhost:8080/");

        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerAssembler sellerAssembler = new SellerAssembler();
        SellerService sellerService = new SellerService(sellerRepository, sellerFactory, sellerAssembler);
        SellerPresenter sellerPresenter = new SellerPresenter();

        ProductRepository productRepository = new ProductRepository();
        ProductFactory productFactory = new ProductFactory();
        ProductService productService = new ProductService(productRepository, sellerRepository, productFactory);

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(ItemNotFoundExceptionsMapper.class)
                .register(InvalidParameterExceptionMapper.class)
                .register(MissingParameterExceptionMapper.class)
                .register(new SellerResource(sellerService, sellerPresenter, uri))
                .register(new ProductResource(productService, uri))
                .register(HealthResource.class)
                .packages("ulaval.glo2003");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }
}
