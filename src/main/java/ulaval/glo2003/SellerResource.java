package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/sellers")
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
        Seller seller = sellerRepository.findSeller(id);

        SellerResponse sellerResponse = SellerAssembler.toResponse(seller);

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }

    @POST
    public Response createSeller(SellerRequest sellerRequest) {
        Seller newSeller = SellerAssembler.fromRequest(sellerRequest);

        sellerRepository.addSeller(newSeller);

        return Response.created(URI.create(baseUri.toString() + "sellers/" + newSeller.id.toString())).build();
    }
}
