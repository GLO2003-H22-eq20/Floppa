package ulaval.glo2003.controllers.seller;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.seller.SellerService;
import ulaval.glo2003.application.seller.dtos.SellerDto;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;
import ulaval.glo2003.controllers.seller.dtos.SellerPresenter;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;

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

        if (sellerRequest.name == null) {
            throw new MissingParameterException("Missing name");
        }
        if (sellerRequest.bio == null) {
            throw new MissingParameterException("Missing bio");
        }
        if (sellerRequest.birthDate == null) {
            throw new MissingParameterException("Missing birthdate");
        }
        return Response.created(URI.create(baseUri.toString() + "sellers/" + sellerId)).build();
    }

    @Path("{id}")
    @GET
    public Response getSeller(@PathParam("id") String id) {
        SellerDto sellerDto = sellerService.getSeller(id);

        SellerResponse sellerResponse = sellerPresenter.presentSeller(sellerDto);

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }
}
