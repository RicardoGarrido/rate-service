package org.company.controllers.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<HttpExceptionResponse> handleHttpException(final HttpException httpException) {
        final HttpExceptionResponse exceptionResponse = HttpExceptionResponse.builder()
                .genericMessage(httpException.getGenericMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, httpException.getHttpStatus());
    }
}
