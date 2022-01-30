package ulaval.glo2003;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Optional;

@Path("sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {

    private SellerRepository sellerRepository;
    private URI baseUri;

    public SellerResource(SellerRepository sellerRepository, URI baseUri) {
        this.sellerRepository = sellerRepository;
        this.baseUri = baseUri;
    }

    @Path("{id}")
    @GET
    public Response getSeller(@PathParam("id") String id) {
        Seller seller = sellerRepository.findById(id);

        SellerResponse sellerResponse = SellerAssembler.toResponse(seller);

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
