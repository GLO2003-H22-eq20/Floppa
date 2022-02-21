package ulaval.glo2003.controllers.product;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.product.ProductService;
import ulaval.glo2003.controllers.product.dtos.ProductPresenter;
import ulaval.glo2003.controllers.product.dtos.ProductRequest;
import ulaval.glo2003.controllers.product.dtos.ProductResponse;
import ulaval.glo2003.domain.ProductCategory;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerProduct;
import ulaval.glo2003.domain.SellerProducts;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                                        @QueryParam("maxPrice") Float maxPrice)  {
        List<SellerProduct> sellerProducts = productService.getProductFiltered(sellerId, title, categories, minPrice, maxPrice);

        List<ProductResponse> productResponses = sellerProducts.stream()
                .map(productPresenter::presentProduct)
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(productResponses).build();
    }
}
