package ulaval.glo2003.product.ui;

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
import ulaval.glo2003.application.offer.OfferService;
import ulaval.glo2003.product.domain.SellerProduct;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.product.ui.request.ProductRequest;
import ulaval.glo2003.product.ui.response.ProductResponse;
import ulaval.glo2003.product.ui.response.ProductResponseAssembler;
import ulaval.glo2003.product.ui.response.ProductsResponse;


import java.net.URI;
import java.util.List;

@Path("products")
public class ProductResource {
    private final ProductService productService;
    private final OfferService offerService;
    private final ProductResponseAssembler productResponseAssembler;
    private final URI baseUri;

    public ProductResource(ProductService productService, OfferService offerService,
                           ProductResponseAssembler productResponseAssembler, URI baseUri) {
        this.productService = productService;
        this.offerService = offerService;
        this.baseUri = baseUri;
        this.productResponseAssembler = productResponseAssembler;
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

        ProductResponse productResponse = productResponseAssembler.presentProduct(sellerProduct);

        return Response.status(Response.Status.OK).entity(productResponse).build();
    }

    @GET
    public Response getFilteredProducts(@QueryParam("sellerId") String sellerId,
                                        @QueryParam("title") String title,
                                        @QueryParam("categories") List<String> categories,
                                        @QueryParam("minPrice") Double minPrice,
                                        @QueryParam("maxPrice") Double maxPrice) {
        try {
            List<SellerProduct> sellersProducts = productService.getFilteredProducts(sellerId,
                                                                                     title,
                                                                                     categories,
                                                                                     minPrice,
                                                                                     maxPrice);

            ProductsResponse productsResponse = productResponseAssembler.presentProducts(sellersProducts);

            return Response.status(Response.Status.OK).entity(productsResponse).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{productId}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffer(@PathParam("productId") String id, @NotNull ulaval.glo2003.controllers.offer.dtos.OfferRequest offerRequest) {
        offerService.createOffer(id, offerRequest);
        return Response.status(Response.Status.OK).build();
    }
}
