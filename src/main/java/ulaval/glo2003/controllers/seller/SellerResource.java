package ulaval.glo2003.controllers.seller;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.seller.SellerService;
import ulaval.glo2003.controllers.seller.dtos.SellerPresenter;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.valueObject.SellerProducts;

import java.net.URI;

@Path("sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
    private final SellerPresenter sellerPresenter;
    private final URI baseUri;
    private final SellerService sellerService;

    public SellerResource(SellerService sellerService, SellerPresenter sellerPresenter, URI baseUri) {
        this.sellerService = sellerService;
        this.sellerPresenter = sellerPresenter;
        this.baseUri = baseUri;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@NotNull SellerRequest sellerRequest) {
        String sellerId = sellerService.createSeller(sellerRequest);

        return Response.created(URI.create(baseUri.toString() + "sellers/" + sellerId)).build();
    }

    @Path("{id}")
    @GET
    public Response getSeller(@PathParam("id") String id) {
        SellerProducts sellerProducts = sellerService.getSeller(id);

        SellerResponse sellerResponse = sellerPresenter.presentSeller(sellerProducts);

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }
}
