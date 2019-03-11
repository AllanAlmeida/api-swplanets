package br.com.apisw.resource;


import static br.com.apisw.util.PlanetUtil.getAPlanet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.model.Planet;
import br.com.apisw.service.PlanetService;
import br.com.apisw.util.SwApiErrorUtil;

public class PlanetResourceTest {

	private HttpServletRequest  request = Mockito.mock(HttpServletRequest.class);
	
	private PlanetService planetService = Mockito.mock(PlanetService.class);
	
	@Autowired
	private PlanetResource planetResource;
	
	@BeforeEach
	public void before() {
		this.planetResource = new PlanetResource(planetService);
	}
	
	@Test
	public void mayObtainSwplanetsList() throws SwApiException {
		List<Planet> listPlanets = Lists.newArrayList(getAPlanet());
		Mockito.when(planetService.findAll()).thenReturn(listPlanets);
		assertNotNull(planetResource.list());	
	}
	
	@Test
	public void mayObtainSwplanetsViewByIdNull() throws SwApiException {
		String id = "1";
		Mockito.when(planetService.findById(id)).thenReturn(null);
		ResponseEntity<Planet> reponse = planetResource.viewById(id);
		assertTrue(reponse.getStatusCode().toString().contains("204"));
	}
	
	@Test
	public void mayObtainSwplanetsViewByIdNotNull() throws SwApiException {
		String id = "1";
		Mockito.when(planetService.findById(id)).thenReturn(getAPlanet());
		assertNotNull(planetResource.viewById(id));	
	}
	
	@Test
	public void mayObtainSwplanetsViewByNameNull() throws SwApiException {
		String planetName = "Earth";
		Mockito.when(planetService.findByName(planetName)).thenReturn(null);
		ResponseEntity<Planet> reponse = planetResource.viewByName(planetName);
		assertTrue(reponse.getStatusCode().toString().contains("204"));
	}
	
	@Test
	public void mayObtainSwplanetsViewByNameNotNull() throws SwApiException {
		String planteName = "Earth";
		Mockito.when(planetService.findByName(planteName)).thenReturn(getAPlanet());
		assertNotNull(planetResource.viewByName(planteName));	
	}
	
	@Test
	public void mayMockSaveAPlanet() throws SwApiException {
		Mockito.doNothing().when(planetService).save(getAPlanet());
		planetResource.save(getAPlanet());
	}
	
	@Test
	public void mayMockDeleteAPlanetById() throws SwApiException {
		String id = "1";
		Mockito.doNothing().when(planetService).delete(id);
		planetResource.deleteById(id);
	}
	
	@Test
	public void mayMockDeleteAPlanetByName() throws SwApiException {
		String namePlanet = "Earth";
		Mockito.doNothing().when(planetService).deleteBy(namePlanet);
		planetResource.deleteByName(namePlanet);
	}
	
	@Test
	public void mayObtainErrorCodeGetPathError() throws SwApiException {
		assertEquals(SwApiErrorUtil.getErrorPathFormated(), planetResource.error(request));
	}
	
	@Test
	public void mayObtainGetErrorPath() throws SwApiException {
		assertEquals("/error", planetResource.getErrorPath());
	}
}
