package br.com.apisw.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.util.SwApiErrorUtil;

public class EndpointControllerTest {
	
	@Autowired
	EndpointController endPointController;
	
	RequestMappingHandlerMapping handlerMapping = Mockito.mock(RequestMappingHandlerMapping.class);
	
	HttpServletRequest  request = Mockito.mock(HttpServletRequest.class);
	
	Model model = Mockito.mock(Model.class);
	
	@BeforeEach
	public void before() {
		this.endPointController = new EndpointController(handlerMapping);
	}
	
	@Test
	public void mayObtainHandlerMethodsFromShow() {
		assertEquals("endpoint", endPointController.show(model));	
	}
	
	@Test
	public void mayObtainErrorCodeGetPathError() throws SwApiException {
		assertEquals(SwApiErrorUtil.getErrorPathFormated(), endPointController.error(request));
	}
	
	@Test
	public void mayObtainErrorCode404() throws SwApiException {
		Mockito.when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(404);
		assertEquals("/error/error-404", endPointController.error(request));
	}

	@Test
	public void mayObtainErrorCode500() throws SwApiException {
		Mockito.when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(500);
		assertEquals("/error/error-500", endPointController.error(request));
	}
	
	@Test
	public void mayObtainErrorCodeNot404Neither500() throws SwApiException {
		Mockito.when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(505);
		assertEquals(SwApiErrorUtil.getErrorPathFormated(), endPointController.error(request));
	}
	
	@Test
	public void mayObtainGetErrorPath() throws SwApiException {
		assertEquals("/error", endPointController.getErrorPath());
	}
}
