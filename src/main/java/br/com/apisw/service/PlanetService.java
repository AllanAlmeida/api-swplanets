package br.com.apisw.service;

import java.util.List;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.model.Planet;

public interface PlanetService {
	
	void delete(String id);
	
	void deleteBy(String planetName) throws SwApiException ;
	
	void save(Planet planet) throws SwApiException;
    
	List<Planet> findAll() throws SwApiException;
    
	Planet findById(String id) throws SwApiException;
    
    Planet findByName(String name) throws SwApiException;
}
