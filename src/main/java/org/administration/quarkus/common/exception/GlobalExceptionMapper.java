package org.administration.quarkus.common.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.LocalDateTime;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        // Para evitar exponer detalles internos en producción, podrías loggear el 'exception.getMessage()'
        // y devolver un mensaje genérico al cliente.
        ExceptionResponse er = new ExceptionResponse(
                LocalDateTime.now(),
                "Ocurrió un error inesperado",
                exception.getMessage() // En desarrollo, esto es útil. En producción, podría ser un riesgo de seguridad.
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(er).build();
    }
}
