package org.company.controllers.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCatalog  {
    ELEMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Element not found");

    private final HttpStatus httpStatus;
    private final String message;


    public HttpException getException() {
        return new HttpException(this.httpStatus, this.message);
    }
}
