package ulaval.glo2003.controllers.exceptionMappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.response.ExceptionResponse;
import ulaval.glo2003.controllers.exceptions.MissingParameterException;

public class MissingParameterExceptionMapper implements ExceptionMapper<MissingParameterException> {
    @Override
    public Response toResponse(MissingParameterException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("MISSING_PARAMETER", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exceptionResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
