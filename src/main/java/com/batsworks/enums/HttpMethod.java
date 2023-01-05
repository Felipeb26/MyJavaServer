package com.batsworks.enums;

public enum HttpMethod {

    GET, HEAD, POST, OPTIONS, PUT, DELETE, PATCH;
    public static final int MAX_LENGTH;

    static {
        int maxAttempt = -1;
        for (HttpMethod method : values()) {
            if (method.name().length() > maxAttempt) {
                maxAttempt = method.name().length();
            }
        }
        MAX_LENGTH = maxAttempt;
    }
}

