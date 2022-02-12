package ulaval.glo2003.controllers.product;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.product.ProductService;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.controllers.product.dtos.ProductRequest;

import java.net.URI;

@Path("products")
public class ProductResource {
    private final ProductService productService;
    private final URI baseUri;

    public ProductResource(ProductService productService, URI baseUri) {
        this.productService = productService;
        this.baseUri = baseUri;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@HeaderParam(value = "X-Seller-Id") String sellerId,
                                  @NotNull ProductRequest productRequest) {
        String productId = productService.createProduct(sellerId, productRequest);

        if (productRequest.title == null) {
            throw new MissingParameterException("Missing title");
        }
        if (productRequest.description == null) {
            throw new MissingParameterException("Missing description");
        }
        if (productRequest.suggestedPrice == null) {
            throw new MissingParameterException("Missing suggested price");
        }

        return Response.created(URI.create(baseUri.toString() + "products/" + productId)).build();
    }
}
