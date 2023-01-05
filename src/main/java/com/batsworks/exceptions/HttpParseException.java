package com.batsworks.exceptions;

import com.batsworks.enums.HttpStatusCode;

public class HttpParseException extends Exception {

    private final HttpStatusCode errorCode;

    public HttpParseException(HttpStatusCode errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }

    public HttpStatusCode getErrorCode() {
        return errorCode;
    }
}
