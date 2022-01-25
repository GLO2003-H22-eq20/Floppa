package ulaval.glo2003;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("sellers")
public class SellerResource {

    private SellerRepository sellerRepository;
    private URI baseUri;

    public SellerResource(SellerRepository sellerRepository, URI baseUri) {
        this.sellerRepository = sellerRepository;
        this.baseUri = baseUri;
    }


    @POST
    public Response createSeller(SellerRequest sellerRequest) {
        Seller newSeller = SellerAssembler.fromRequest(sellerRequest);

        sellerRepository.addSeller(newSeller);

        return Response.created(URI.create(baseUri.toString() + "sellers/" + newSeller.id.toString())).build();
    }

}
