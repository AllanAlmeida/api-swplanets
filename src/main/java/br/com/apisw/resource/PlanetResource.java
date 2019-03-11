package br.com.apisw.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.model.Planet;
import br.com.apisw.service.PlanetService;
import br.com.apisw.util.SwApiErrorUtil;

@RestController
@RequestMapping("/swplanets")
public class PlanetResource implements ErrorController {
	
	@Autowired
	private PlanetService planetService;
	
	public PlanetResource(PlanetService planetService) {
		this.planetService = planetService;
	}
	 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<?> list() throws SwApiException{
		return ResponseEntity.ok(planetService.findAll());
	}
		
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Planet> viewById(@PathVariable String id) throws SwApiException{
		Planet planet = planetService.findById(id);
		if(planet==null){
			return ResponseEntity.noContent().build();	
		} else {
			return ResponseEntity.ok(planet);
		}
	}
	
	@RequestMapping(value = "/name/{planetName}", method = RequestMethod.GET)
	public ResponseEntity<Planet> viewByName(@PathVariable String planetName) throws SwApiException{
	    Planet planet = planetService.findByName(planetName);
		if(planet==null){
			return ResponseEntity.noContent().build();	
		} else {
			return ResponseEntity.ok(planet);
		}
	}
			
	@RequestMapping(value = "/deleteby/id", method = RequestMethod.DELETE)
	public void deleteById(@RequestBody String id) throws SwApiException{
		planetService.delete(id);
	}
	
	@RequestMapping(value = "/deleteby/name", method = RequestMethod.DELETE)
	public void deleteByName(@RequestBody String namePlanet) throws SwApiException{
		planetService.deleteBy(namePlanet);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public void save(@RequestBody Planet planet) throws SwApiException {
		planetService.save(planet);
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
