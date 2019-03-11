package br.com.apisw.service;

import br.com.apisw.exception.SwApiException;

public interface SwApiService {
	
	int getQuantityInMoviesBy(String planetName) throws SwApiException;
	
}
