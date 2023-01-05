package com.batsworks.exceptions;

public class HttpConfigurationException extends RuntimeException {

	public HttpConfigurationException() {
	}

	public HttpConfigurationException(String message) {
		super(message);
	}

	public HttpConfigurationException(String message, Throwable throwable) {
		super(throwable);
	}

	public HttpConfigurationException(Throwable throwable) {
		super(throwable);
	}
}
