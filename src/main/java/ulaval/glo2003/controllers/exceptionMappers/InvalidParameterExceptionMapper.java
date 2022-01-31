package ulaval.glo2003.controllers.exceptionMappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.response.ExceptionResponse;

import java.security.InvalidParameterException;

public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {
    @Override
    public Response toResponse(InvalidParameterException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_PARAMETER", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();    }
}
