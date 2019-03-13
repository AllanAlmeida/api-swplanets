package br.com.apisw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@PropertySource({ "classpath:mongodb-data-source.properties" })
public class MongoConfig {

    @Autowired
    Environment env;
    
    public @Bean MongoTemplate mongoTemplate() throws Exception {
    	
    	MongoClient mongoClient = new MongoClient(new MongoClientURI(env.getProperty("mongo.uri")));
    	return 	new MongoTemplate(mongoClient, env.getProperty("mongo.dbname"));
    }
}
