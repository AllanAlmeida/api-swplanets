package br.com.apisw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.apisw.exception.SwApiException;

@SpringBootApplication
public class ApiSwApplication {

	public static void main(String[] args) throws SwApiException {
		SpringApplication.run(ApiSwApplication.class, args);
	}
}
