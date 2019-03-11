package br.com.apisw.exception;

public class SwApiException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SwApiException(String message) {
		super(message);
	}

	public SwApiException(String message, Exception e) {
		super(message, e);
	}
}
