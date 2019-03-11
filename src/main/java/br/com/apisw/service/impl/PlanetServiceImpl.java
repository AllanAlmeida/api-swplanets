package br.com.apisw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.model.Planet;
import br.com.apisw.repository.PlanetRepository;
import br.com.apisw.service.PlanetService;
import br.com.apisw.service.SwApiService;

@Service
public class PlanetServiceImpl implements PlanetService {

	private static String PLANETS = "planets";
	
	@Autowired
    private PlanetRepository planetRepository;
	
	@Autowired 
	private MongoOperations mongoOperations;
	
	@Autowired
	private SwApiService swApiService;
	
     
	public PlanetServiceImpl(SwApiService swApiService, PlanetRepository planetRepository, MongoOperations mongoOperations) {
		this.swApiService = swApiService;
		this.planetRepository = planetRepository;
		this.mongoOperations = mongoOperations;
	}
	
    public void save(Planet planet) throws SwApiException {
    	planet.setMovieTimes(swApiService.getQuantityInMoviesBy(planet.getName()));
        planetRepository.save(planet);
    }
    
    public List<Planet> findAll() throws SwApiException {
    	List<Planet> listPlanets = new ArrayList<>();
    	List<Planet> listReturn = new ArrayList<>();
    	
    	listPlanets.addAll((List<Planet>) planetRepository.findAll()); 
    	
    	if(!listPlanets.isEmpty()) {
    		
    		for (Planet planets : listPlanets) {
    			Planet planet = planets;
    			planet.setMovieTimes(swApiService.getQuantityInMoviesBy(planet.getName()));
    			listReturn.add(planet);
			}
    	}
    	
    	return listReturn;
    }
 
    public Planet findById(String id) throws SwApiException {
    	return queryPlanet("_id", id);
    }
    
    public Planet findByName(String name) throws SwApiException {
    	return queryPlanet("name", name);
    }
 
    public void delete(String id) {
        planetRepository.deleteById(id);
    }
    
    public void deleteBy(String planetName) throws SwApiException {
    	planetRepository.delete(this.findByName(planetName));
    }
    
    private Planet queryPlanet(String filter, String value) throws SwApiException {
		Planet planet = new Planet();
    	planet = mongoOperations.findOne(Query.query(Criteria.where(filter).is(value)), Planet.class, PLANETS);
    	
    	if(planet==null) {
    		return planet;
    	}
    	
    	planet.setMovieTimes(swApiService.getQuantityInMoviesBy(planet.getName()));
    	return planet;
	}
}
