package ulaval.glo2003.health;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.health.response.HealthResponse;

@Path("health")
public class HealthResource {
    private DatastoreProvider datastoreProvider;

    public HealthResource(DatastoreProvider datastoreProvider) {
        this.datastoreProvider = datastoreProvider;
    }

    @GET
    public Response getHealth() {
        try {
            datastoreProvider.getDatastore().getDatabase().listCollectionNames().first();
        }catch (Exception e){
            return Response.serverError().entity(new HealthResponse(true, false)).build();
        }
        return Response.status(Response.Status.OK).entity(new HealthResponse(true, true)).build();
    }
}
