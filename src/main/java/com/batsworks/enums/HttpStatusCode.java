package com.batsworks.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum HttpStatusCode {

    OK(200, "OK"),
    CREATED(201, "Created"),
    ACEPTED(202, "Accepted"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    TOO_LONG(414, "URI Too Long"),
    SERVER_ERROR(500, "Server Error"),
    SERVER_NOT_IMPLEMENTED(501, "Server not implemeted");

    public final int STATUS_CODE;
    public final String MESSAGE;

    HttpStatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
