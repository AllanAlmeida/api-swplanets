package br.com.apisw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apisw.model.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {

}
