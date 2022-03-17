package ulaval.glo2003.exceptions.mappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.exceptions.mappers.response.ExceptionResponse;
import ulaval.glo2003.exceptions.InvalidParameterException;

public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {
    @Override
    public Response toResponse(InvalidParameterException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionResponse("INVALID_PARAMETER", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
