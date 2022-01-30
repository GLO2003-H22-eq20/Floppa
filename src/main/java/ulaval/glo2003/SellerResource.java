package ulaval.glo2003;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {

    private SellerRepository sellerRepository;
    private URI baseUri;

    public SellerResource(SellerRepository sellerRepository, URI baseUri) {
        this.sellerRepository = sellerRepository;
        this.baseUri = baseUri;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@NotNull SellerRequest sellerRequest) {
        Seller newSeller = SellerAssembler.fromRequest(sellerRequest);

        sellerRepository.addSeller(newSeller);

        return Response.created(URI.create(baseUri.toString() + "sellers/" + newSeller.id.toString())).build();
    }

}
