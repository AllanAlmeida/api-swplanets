package br.com.apisw.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import br.com.apisw.exception.SwApiException;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class MongoDBEmbededUtil {
	
	private static final Logger LOG = LogManager.getLogger();
	
	private static final int PORT = 27017;
	
	private static final String IP_HOST = "localhost";
	
	private static final String TEST_DB = "test";
	
	private static MongodExecutable mongodExecutable;
	
    private static MongoTemplate mongoTemplate = null;
	
	public static MongoTemplate start() throws SwApiException {
		
		try {
        	IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net(IP_HOST, PORT, Network.localhostIsIPv6())).build();
     
            MongodStarter starter = MongodStarter.getDefaultInstance();
            mongodExecutable = starter.prepare(mongodConfig);
			mongodExecutable.start();
			mongoTemplate = new MongoTemplate(new MongoClient(IP_HOST, PORT), TEST_DB);
			
		} catch (IOException e) {
			LOG.info("Fail to connect embeded MongoDB", e);
			throw new SwApiException("Fail to connect embeded MongoDB", e);
		}
        
        return mongoTemplate;   
	}

	public static void stop() {
		mongodExecutable.stop();
	}

}
