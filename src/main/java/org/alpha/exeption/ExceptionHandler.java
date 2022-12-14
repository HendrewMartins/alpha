package org.alpha.exeption;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception exception){
    if(exception instanceof notFoundMessageExeption){
        return Response.status(Response.Status.NOT_FOUND).entity
        (new ErrorResponseBody(exception.getMessage())).build();
    }
}

    @Override
    public Response toResponse(Exception exception) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static final class ErrorResponseBody{
        private final String message;

        public ErrorResponseBody(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
    }
}
