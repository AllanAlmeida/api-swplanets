package br.com.apisw.util;

import br.com.apisw.builder.PlanetsBuilder;
import br.com.apisw.model.Planet;

public class PlanetUtil {

	public static Planet getAPlanet() {
		return new PlanetsBuilder()
				.id("1")
				.name("Earth")
				.climate("In fact Hot")
				.terrain("hard")
				.movieTimes(1)
				.builder();
	}
}
