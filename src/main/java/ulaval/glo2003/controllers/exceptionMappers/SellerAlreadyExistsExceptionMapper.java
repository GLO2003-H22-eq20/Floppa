package ulaval.glo2003.controllers.exceptionMappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.controllers.exceptionMappers.response.ExceptionResponse;
import ulaval.glo2003.controllers.exceptions.SellerAlreadyExistsException;

public class SellerAlreadyExistsExceptionMapper  implements ExceptionMapper<SellerAlreadyExistsException> {
    @Override
    public Response toResponse(SellerAlreadyExistsException sellerAlreadyExistsException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("SELLER_ALREADY_EXISTS","The seller already exists");
        return Response.status(Response.Status.CONFLICT).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
