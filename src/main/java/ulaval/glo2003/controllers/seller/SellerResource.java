package ulaval.glo2003.controllers.seller;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.controllers.seller.dtos.SellerAssembler;
import ulaval.glo2003.controllers.seller.dtos.SellerRequest;
import ulaval.glo2003.controllers.seller.dtos.SellerResponse;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.infrastructure.SellerRepository;

import java.net.URI;

@Path("sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
    private final SellerRepository sellerRepository;
    private final SellerPresenter sellerPresenter;
    private final URI baseUri;

    public SellerResource(SellerRepository sellerRepository, SellerPresenter sellerPresenter, URI baseUri) {
        this.sellerRepository = sellerRepository;
        this.sellerPresenter = sellerPresenter;
        this.baseUri = baseUri;
    }

    @Path("{id}")
    @GET
    public Response getSeller(@PathParam("id") String id) {
        Seller seller = sellerRepository.findById(id);

        SellerResponse sellerResponse = sellerPresenter.presentSeller(seller);

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@NotNull SellerRequest sellerRequest) {
        Seller seller = SellerAssembler.fromRequest(sellerRequest);

        sellerRepository.saveSeller(seller);

        return Response.created(URI.create(baseUri.toString() + "sellers/" + seller.getId().toString())).build();
    }
}
