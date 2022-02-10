package ulaval.glo2003.controllers.product;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.controllers.product.dtos.ProductAssembler;
import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.infrastructure.ProductRepository;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.net.URI;


@Path("products")
public class ProductResource {

    private SellerRepository sellerRepository;
    private ProductRepository productRepository;
    private ProductPresenter productPresenter;
    private URI baseUri;

    public ProductResource(SellerRepository sellerRepository, ProductPresenter productPresenter, ProductRepository productRepository, URI baseUri) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.productPresenter = productPresenter;
        this.baseUri = baseUri;
    }

    @Path("{id}")
    @GET
    public Response getProduct(@PathParam("id") String id) {
        Product product = productRepository.findById(id);

        ProductResponse productResponse = productPresenter.presentProduct(product);

        return Response.status(Response.Status.OK).entity(productResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@HeaderParam("X-Seller-Id") String sellerId, @NotNull ProductRequest productRequest) {
        Seller seller = sellerRepository.findById(sellerId);
        Product product = ProductAssembler.fromRequest(productRequest, seller);
        productRepository.saveProduct(product);
        seller.addProduct(product);

        return Response.created(URI.create(baseUri.toString() + "products/" + product.getId().toString())).build();
    }


}
