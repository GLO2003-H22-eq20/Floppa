package ulaval.glo2003;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ItemNotFoundExceptionsMapper implements ExceptionMapper<ItemNotFoundException> {
    @Override
    public Response toResponse(ItemNotFoundException itemNotFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("ITEM_NOT_FOUND","The item cannot be found (ID is invalid or null).");
        return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
