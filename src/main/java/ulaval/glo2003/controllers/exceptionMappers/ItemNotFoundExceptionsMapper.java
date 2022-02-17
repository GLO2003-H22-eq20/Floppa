package ulaval.glo2003.controllers.exceptionMappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.response.ExceptionResponse;
import ulaval.glo2003.controllers.exceptions.ItemNotFoundException;

public class ItemNotFoundExceptionsMapper implements ExceptionMapper<ItemNotFoundException> {
    @Override
    public Response toResponse(ItemNotFoundException itemNotFoundException) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionResponse("ITEM_NOT_FOUND","The item cannot be found (ID is invalid or null)."))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
