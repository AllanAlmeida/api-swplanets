package br.com.apisw.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoOperations;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.model.Planet;
import br.com.apisw.repository.PlanetRepository;
import br.com.apisw.service.SwApiService;
import br.com.apisw.service.impl.PlanetServiceImpl;
import br.com.apisw.util.PlanetUtil;

/**
 * This class uses a real MongoDB for integration.
 * So, may we need a MongoDB installation with:<br>
 * DB: swplanets<br>
 * COLLECTION: planets
 * 
 * Before Run, uncomment all annotations.
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class PlanetServiceImplIntegrationTest {
	
	//@Autowired
	private PlanetServiceImpl planetServiceImpl;
	
	private PlanetRepository planetRepository = Mockito.mock(PlanetRepository.class);
	
	private MongoOperations mongoOperations = Mockito.mock(MongoOperations.class);
	
	private SwApiService swApiService = Mockito.mock(SwApiService.class);
	
	//@Test
	public void maySaveAPlanet() throws SwApiException {
		Planet planet = PlanetUtil.getAPlanet();
		planetServiceImpl.save(PlanetUtil.getAPlanet());
		Planet planetFromDB = planetServiceImpl.findById(planet.getId());
		assertEquals(planet.getName(), planetFromDB.getName());
	}
	
	//@Test
	public void mayListPlanetsNull() throws SwApiException {
		planetServiceImpl.delete(PlanetUtil.getAPlanet().getId());
		List<Planet> listPlanets = planetServiceImpl.findAll();
		assertTrue(listPlanets.isEmpty());
		
		maySaveAPlanet();
	}
	
	//@Test
	public void mayListPlanetsNotNull() throws SwApiException {
		List<Planet> listPlanets = planetServiceImpl.findAll();
		assertNotNull(listPlanets);
	}
	
	//@Test
	public void mayFindAPlanetById() throws SwApiException {
		Planet planetFromDB = planetServiceImpl.findById(PlanetUtil.getAPlanet().getId());
		assertNotNull(planetFromDB);
	}
	
	//@Test
	public void mayFindAPlanetByName() throws SwApiException {
		Planet planetFromDB = planetServiceImpl.findByName(PlanetUtil.getAPlanet().getName());
		assertNotNull(planetFromDB);
	}
	
	//@Test
	public void mayDeleteAPlanetByName() throws SwApiException {
		planetServiceImpl.deleteBy(PlanetUtil.getAPlanet().getName());
		Planet planetFromDB = planetServiceImpl.findByName(PlanetUtil.getAPlanet().getName());
		assertTrue(planetFromDB==null);
		
		maySaveAPlanet();
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
