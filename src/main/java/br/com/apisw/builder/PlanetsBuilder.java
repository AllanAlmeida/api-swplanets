package br.com.apisw.builder;

import br.com.apisw.model.Planet;

public class PlanetsBuilder {

	Planet planet;
	
	public PlanetsBuilder(){
		this.planet = new Planet();
	}
	
	public PlanetsBuilder id(String id) {
		planet.setId(id);
		return this;
	}
	
	public PlanetsBuilder name(String name) {
		planet.setName(name);
		return this;
	}
	
	public PlanetsBuilder climate(String climate) {
		planet.setClimate(climate);
		return this;
	}
	
	public PlanetsBuilder terrain(String terrain) {
		planet.setTerrain(terrain);
		return this;
	}
	
	public PlanetsBuilder movieTimes(int movieTimes) {
		planet.setMovieTimes(movieTimes);
		return this;
	}
	
	public Planet builder() {
		return planet;
	}
}
