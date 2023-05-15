package org.company.controllers.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String genericMessage;

    public HttpException(HttpStatus httpStatus, String genericMessage) {
        this.httpStatus = httpStatus;
        this.genericMessage = genericMessage;
    }

}
