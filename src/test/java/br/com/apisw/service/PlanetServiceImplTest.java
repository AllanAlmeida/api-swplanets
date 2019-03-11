package br.com.apisw.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.model.Planet;
import br.com.apisw.repository.PlanetRepository;
import br.com.apisw.util.MongoDBEmbededUtil;
import br.com.apisw.util.PlanetUtil;

/**
 * This class uses a embebed MongoDB engine.
 */
public class PlanetServiceImplTest {

	private PlanetRepository planetRepository = Mockito.mock(PlanetRepository.class);
	
	private MongoOperations mongoOperations = Mockito.mock(MongoOperations.class);
	
	private SwApiService swApiService = Mockito.mock(SwApiService.class);
	
	private MongoTemplate mongoTemplate;

	@BeforeEach
	public void setup() throws SwApiException {
		this.mongoTemplate = MongoDBEmbededUtil.start();
	}
	 
	@AfterEach
    void clean() throws SwApiException {
        MongoDBEmbededUtil.stop();
    }
	
	@Test
	public void maySaveAPlanet() throws SwApiException {
		Planet planet = PlanetUtil.getAPlanet();
		mongoTemplate.save(PlanetUtil.getAPlanet());
		Planet planetFromDB = findOneById("_id");
		assertEquals(planet.getName(), planetFromDB.getName());
	}
	
	@Test
	public void mayListPlanetsNull() throws SwApiException {
		mongoTemplate.remove(PlanetUtil.getAPlanet());
		List<Planet> listPlanets = mongoTemplate.findAll(Planet.class);
		assertTrue(listPlanets.isEmpty());
		
		maySaveAPlanet();
	}
	
	@Test
	public void mayListPlanetsNotNull() throws SwApiException {
		maySaveAPlanet();
		List<Planet> listPlanets = mongoTemplate.findAll(Planet.class);
		assertNotNull(listPlanets);
	}
	
	@Test
	public void mayFindAPlanetById() throws SwApiException {
		maySaveAPlanet();
		Planet planetFromDB = findOneById("_id");
		assertNotNull(planetFromDB);
	}
	
	@Test
	public void mayFindAPlanetByName() throws SwApiException {
		maySaveAPlanet();
		Planet planetFromDB = findOneByName("name");
		assertNotNull(planetFromDB);
	}
	
	@Test
	public void mayDeleteAPlanetByName() throws SwApiException {
		maySaveAPlanet();
		mongoTemplate.remove(Query.query(Criteria.where("name").is(PlanetUtil.getAPlanet().getName())), Planet.class, "planets");
		Planet planetFromDB = findOneById("name");
		assertTrue(planetFromDB==null);
	}
	
	private Planet findOneById(String id) {
		return mongoTemplate.findOne(Query.query(Criteria.where(id).is(PlanetUtil.getAPlanet().getId())), Planet.class, "planets");
	}
	
	private Planet findOneByName(String name) {
		return mongoTemplate.findOne(Query.query(Criteria.where(name).is(PlanetUtil.getAPlanet().getName())), Planet.class, "planets");
	}
	
	public PlanetRepository getPlanetRepository() {
		return planetRepository;
	}

	public void setPlanetRepository(PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public SwApiService getSwApiService() {
		return swApiService;
	}

	public void setSwApiService(SwApiService swApiService) {
		this.swApiService = swApiService;
	}
}
