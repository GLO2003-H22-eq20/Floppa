package ulaval.glo2003.controllers.product;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.controllers.product.dtos.ProductAssembler;
import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.infrastructure.ProductRepository;

import java.net.URI;


@Path("products")
public class ProductResource {

    private ProductRepository productRepository;
    private URI baseUri;


    public ProductResource(ProductRepository productRepository, URI baseUri) {
        this.productRepository = productRepository;
        this.baseUri = baseUri;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@HeaderParam("X-Seller-Id") String sellerId, @NotNull ProductRequest productRequest) {
        Product product = ProductAssembler.fromRequest(productRequest);

        productRepository.saveProduct(product);

        return Response.created(URI.create(baseUri.toString() + "products/" + product.getId().toString())).build();
    }


}
