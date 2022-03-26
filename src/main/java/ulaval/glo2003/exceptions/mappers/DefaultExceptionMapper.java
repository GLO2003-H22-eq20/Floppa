package ulaval.glo2003.exceptions.mappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.exceptions.ItemNotFoundException;
import ulaval.glo2003.exceptions.mappers.response.ExceptionResponse;

public class DefaultExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.status(Response.Status.fromStatusCode(500))
                .entity(new ExceptionResponse("UNCAUGHT EXCEPTION", exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
