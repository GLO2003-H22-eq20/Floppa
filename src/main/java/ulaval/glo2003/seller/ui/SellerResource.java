package ulaval.glo2003.seller.ui;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.exceptions.MissingParameterException;
import ulaval.glo2003.seller.domain.SellerProducts;
import ulaval.glo2003.seller.domain.SellerService;
import ulaval.glo2003.seller.ui.request.SellerRequest;
import ulaval.glo2003.seller.ui.response.SellerResponse;
import ulaval.glo2003.seller.ui.response.SellerResponseAssembler;

import java.net.URI;

@Path("sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
    private final SellerResponseAssembler sellerResponseAssembler;
    private final URI baseUri;
    private final SellerService sellerService;

    public SellerResource(SellerService sellerService, SellerResponseAssembler sellerResponseAssembler, URI baseUri) {
        this.sellerService = sellerService;
        this.sellerResponseAssembler = sellerResponseAssembler;
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
        if (id.isBlank()) {
            throw new MissingParameterException("Missing 'seller' ID");
        }
        SellerProducts sellerProducts = sellerService.getSeller(id);

        SellerResponse sellerResponse = sellerResponseAssembler.presentSeller(sellerProducts);

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }

    @Path("@me")
    @GET
    public Response getCurrentSeller(@HeaderParam(value = "X-Seller-Id") String id) {
        if (id == null || id.isBlank()) {
            throw new MissingParameterException("Missing 'seller' ID");
        }
        SellerProducts sellerProducts = sellerService.getSeller(id);

        SellerResponse sellerResponse = sellerResponseAssembler.presentCurrentSeller(sellerProducts);

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }
}