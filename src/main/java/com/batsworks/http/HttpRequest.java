package com.batsworks.http;

import com.batsworks.enums.HttpMethod;
import com.batsworks.enums.HttpStatusCode;
import com.batsworks.exceptions.HttpParseException;
import lombok.Getter;
import lombok.Setter;

public class HttpRequest extends HttpMessage {

    @Getter
    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest() {
    }

    void setMethod(String methodName) throws HttpParseException {
        for (HttpMethod method : HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = HttpMethod.valueOf(methodName);
                return;
            }
        }
        throw new HttpParseException(HttpStatusCode.SERVER_NOT_IMPLEMENTED);
    }

}
