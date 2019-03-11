package br.com.apisw.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SwApiExceptionTest {
	
	@Test
	public void mayObtainSwApiExceptionByMessage() {
		SwApiException swApiException = new SwApiException("Exception");
		assertEquals("Exception", swApiException.getMessage());
	}
	
	@Test
	public void mayObtainSwApiExceptionByMessageAndException() {
		SwApiException swApiException = new SwApiException("Exception", new Exception());
		assertEquals("Exception", swApiException.getMessage());
	}

}
