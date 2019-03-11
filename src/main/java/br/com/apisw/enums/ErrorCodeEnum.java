package br.com.apisw.enums;

public enum ErrorCodeEnum {
	
	CODE_404("404"),
	
	CODE_500("500"),
	
	ERROR_404("error-404"),
	
	ERROR_500("error-500");
	
	public String code;
    
	ErrorCodeEnum(String code) {
		this.code = code;
    }
	
	public String getCode(){
		return code;
	}
}
