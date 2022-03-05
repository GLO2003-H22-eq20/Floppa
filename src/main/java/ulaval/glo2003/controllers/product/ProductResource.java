package ulaval.glo2003.controllers.product;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.product.ProductService;
import ulaval.glo2003.controllers.product.dtos.ProductPresenter;
import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.controllers.product.dtos.ProductsResponse;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.valueObject.SellerProduct;

import java.net.URI;
import java.util.List;

@Path("products")
public class ProductResource {
    private final ProductService productService;
    private final ProductPresenter productPresenter;
    private final URI baseUri;

    public ProductResource(ProductService productService, ProductPresenter productPresenter, URI baseUri) {
        this.productService = productService;
        this.baseUri = baseUri;
        this.productPresenter = productPresenter;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@HeaderParam(value = "X-Seller-Id") String sellerId,
            @NotNull ProductRequest productRequest) {
        String productId = productService.createProduct(sellerId, productRequest);

        return Response.created(URI.create(baseUri.toString() + "products/" + productId)).build();
    }

    @Path("{id}")
    @GET
    public Response getProduct(@PathParam("id") String id) {
        SellerProduct sellerProduct = productService.getProduct(id);

        ProductResponse productResponse = productPresenter.presentProduct(sellerProduct);

        return Response.status(Response.Status.OK).entity(productResponse).build();
    }

    @GET
    public Response getFilteredProducts(@QueryParam("sellerId") String sellerId,
            @QueryParam("title") String title,
            @QueryParam("categories") List<ProductCategory> categories,
            @QueryParam("minPrice") Float minPrice,
            @QueryParam("maxPrice") Float maxPrice) {
        List<SellerProduct> sellersProducts = productService.getFilteredProducts(sellerId,
                title,
                categories,
                minPrice,
                maxPrice);

        ProductsResponse productsResponse = productPresenter.presentProducts(sellersProducts);

        return Response.status(Response.Status.OK).entity(productsResponse).build();
    }
}
