package br.com.apisw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@PropertySource({ "classpath:mongodb-data-source.properties" })
public class MongoConfig {

    @Autowired 
    Environment env;

    public @Bean MongoTemplate mongoTemplate() throws Exception {
    	return 	new MongoTemplate(new MongoClient(env.getProperty("mongo.host")), env.getProperty("mongo.dbname"));
	}
}
