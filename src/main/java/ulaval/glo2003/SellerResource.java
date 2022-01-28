package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Optional;

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
        Optional<Seller> seller = sellerRepository.findById(id);

        SellerResponse sellerResponse = SellerAssembler.toResponse(seller.get());

        return Response.status(Response.Status.OK).entity(sellerResponse).build();
    }

    @POST
    public Response createSeller(SellerRequest sellerRequest) {
        Seller seller = SellerAssembler.fromRequest(sellerRequest);

        sellerRepository.saveSeller(seller);

        return Response.created(URI.create(baseUri.toString() + "sellers/" + seller.id.toString())).build();
    }
}
