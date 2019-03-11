package br.com.apisw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planets")
public class Planet {
	
	@Id
	String id;
	
	String name;
	
	String climate;
	
	String terrain;
	
	int movieTimes;

	public Planet() {
		//Default constructor
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public int getMovieTimes() {
		return movieTimes;
	}

	public void setMovieTimes(int movieTimes) {
		this.movieTimes = movieTimes;
	}
}
