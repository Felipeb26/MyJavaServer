package com.batsworks.http;

import com.batsworks.enums.HttpMethod;
import com.batsworks.enums.HttpStatusCode;
import com.batsworks.exceptions.HttpParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;

    public HttpRequest parseRequest(InputStream inputStream) throws IOException, HttpParseException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();

        parserRequestLine(reader, request);
        parseRequestHeader(reader, request);
        parseRequestBody(reader, request);
        return request;
    }

    private void parserRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParseException {
        StringBuilder builder = new StringBuilder();
        int _byte;

        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        while ((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();
            }
            if (_byte == LF) {

                LOGGER.debug("Request VERSION to Process {}", builder.toString());
                if (!methodParsed || !requestTargetParsed) {
                    throw new HttpParseException(HttpStatusCode.BAD_REQUEST);
                }
                return;
            }
            if (_byte == SP) {
                if (!methodParsed) {
                    LOGGER.debug("Request METHOD to Process {}", builder.toString());
                    request.setMethod(builder.toString());
                    methodParsed = true;
                } else if (!requestTargetParsed) {
                    LOGGER.debug("Request RQUEST TARGET to Process {}", builder.toString());
                    requestTargetParsed = true;
                } else {
                    throw new HttpParseException(HttpStatusCode.BAD_REQUEST);
                }
                builder.delete(0, builder.length());
            } else {
                builder.append((char) _byte);
                if (!methodParsed) {
                    if (builder.length() > HttpMethod.MAX_LENGTH) {
                        throw new HttpParseException(HttpStatusCode.SERVER_NOT_IMPLEMENTED);
                    }
                }
            }
        }
    }

    private void parseRequestHeader(InputStreamReader reader, HttpRequest request) {

    }

    private void parseRequestBody(InputStreamReader reader, HttpRequest request) {

    }
}
