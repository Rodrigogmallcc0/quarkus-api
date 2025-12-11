package org.administration.quarkus.common.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.LocalDateTime;

@Provider
public class ModelNotFoundExceptionMapper implements ExceptionMapper<ModelNotFoundException> {

    @Override
    public Response toResponse(ModelNotFoundException exception) {
        ExceptionResponse er = new ExceptionResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                "Recurso no encontrado" // Detalle genérico para 404
        );
        return Response.status(Response.Status.NOT_FOUND).entity(er).build();
    }
}
