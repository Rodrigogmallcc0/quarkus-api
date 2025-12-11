package org.administration.quarkus.common.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        // Concatenamos todos los mensajes de error de las violaciones
        String mensaje = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        // Opcional: Podrías querer incluir qué campo falló:
        // .map(v -> v.getPropertyPath() + ": " + v.getMessage())

        ExceptionResponse er = new ExceptionResponse(
                LocalDateTime.now(),
                "Error de validación",
                mensaje
        );
        
        return Response.status(Response.Status.BAD_REQUEST).entity(er).build();
    }
}
