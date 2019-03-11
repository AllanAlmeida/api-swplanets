package br.com.apisw.util;

import static br.com.apisw.enums.ErrorCodeEnum.CODE_404;
import static br.com.apisw.enums.ErrorCodeEnum.CODE_500;
import static br.com.apisw.enums.ErrorCodeEnum.ERROR_404;
import static br.com.apisw.enums.ErrorCodeEnum.ERROR_500;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

public class SwApiErrorUtil {
	
	public static final String ERROR = "/error";
	
	public static String error(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (status != null) {
	        
	    	Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	          
	        	return getErrorCodeBy(CODE_404.getCode());
	        
	        } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	
	        	return getErrorCodeBy(CODE_500.getCode());
	        }
	    }
	    return getErrorPathFormated();
	}
	
	public static String getErrorPath() {
	    return ERROR;
	}
	
	private static String getErrorCodeBy(String code) {
		
		String errorCode = StringUtils.EMPTY;
		
		switch(code) {
		
			case "404":
				errorCode = ERROR_404.getCode();
				break;
				
			case "500":
				errorCode = ERROR_500.getCode();
				break;
		}
		
		return ERROR + "/" + errorCode;
	}
	
	public static String getErrorPathFormated() {
		
		return "<html><head><title>SWAPI</title></head><body><div class=\"container\"><div class=\"container\"><h1>ERROR HANDLING SWPLANET</h1></div></div></body></html>";
	}
}
