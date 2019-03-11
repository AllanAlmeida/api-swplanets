package br.com.apisw.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.apisw.model.Planet;

public class PlanetsBuilderTest {
	
	@Test
	public void mayGetPlanetFromBuilder() {
		Planet planet = new PlanetsBuilder()
				.id("1")
				.name("Terra")
				.climate("In fact Hot")
				.terrain("hard")
				.movieTimes(1)
				.builder();
		
		assertNotNull(planet);	
	}

}
