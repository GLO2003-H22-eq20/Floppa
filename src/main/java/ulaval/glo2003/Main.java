package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        URI uri = URI.create("http://localhost:8080/");
        SellerRepository sellerRepository = new SellerRepository();
        ResourceConfig resourceConfig = new ResourceConfig()
                .register(new SellerResource(sellerRepository, uri))
                .packages("ulaval.glo2003");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }

}
