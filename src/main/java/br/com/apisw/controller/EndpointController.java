package br.com.apisw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.util.SwApiErrorUtil;

@Controller
public class EndpointController implements ErrorController {

	private final static String ENDPOINT = "endpoint";
	
    private final static String HANDLER_METHODS = "handlerMethods";
    
    private final RequestMappingHandlerMapping handlerMapping;
	 
	@Autowired
	public EndpointController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}
	  
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute(HANDLER_METHODS, handlerMapping.getHandlerMethods());
		return ENDPOINT;
	}
	
	@RequestMapping(value = SwApiErrorUtil.ERROR)
	public String error(HttpServletRequest request) throws SwApiException {
		return SwApiErrorUtil.error(request);
	}
	
	@Override
	public String getErrorPath() {
		return SwApiErrorUtil.getErrorPath();
	}
}
