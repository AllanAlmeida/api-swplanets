package br.com.apisw.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.apisw.exception.SwApiException;
import br.com.apisw.service.SwApiService;

@Service
@PropertySource({ "classpath:http.properties" })
public class SwApiServiceImpl implements SwApiService, JsonDeserializer<Integer> {

	private static final Logger LOG = LogManager.getLogger();
	
	private static final String URL_SWAPI = "url.swapi";
	
	private static final String RESULTS = "results";
	
	private static final String FILMS = "films";
	
	private static final String COUNT = "count";
	
	@Autowired
	private Environment env;
	
	public SwApiServiceImpl() {
		//Default constructor
	}
	
	@Override
	public Integer deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
		
		int qty = 0;
		
		if(element==null){
			return qty;
		}
		
		if(element.isJsonObject()){
		
			JsonObject jsonObject = element.getAsJsonObject();
			
			if(jsonObject.get(COUNT).getAsInt()>0) {
				
				jsonObject = jsonObject.get(RESULTS).getAsJsonArray().get(0).getAsJsonObject();
				
				for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) { 
				
					if(FILMS.equals(entry.getKey())) {
						qty = entry.getValue().getAsJsonArray().size();
					}
				}
			}
		}
		
		return qty;
	}

	@Override
	public int getQuantityInMoviesBy(String planetName) throws SwApiException {
		
		int qtyInMovies = 0;
		String urlString = null;
		
		try {
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			
			urlString = getUrl(planetName);
			
			HttpResponse response = httpClient.execute(new HttpGet(urlString));
			
			BufferedReader buffered = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); 
			
			GsonBuilder builder = new GsonBuilder(); 
			
			Gson gson = builder.registerTypeAdapter(Integer.class, new SwApiServiceImpl()).create();
			
			qtyInMovies = gson.fromJson(buffered, Integer.class);
			
		} catch(Exception e) {
			LOG.info("Timeout to conect service: [URL] " + urlString +"", e);
			throw new SwApiException("Timeout to conect the service: sw-api.co", e);
		}
		
		return qtyInMovies;
		 
	}
	
	private String getUrl(String planetName){
		String url = env.getProperty(URL_SWAPI) + planetName;
		LOG.info(url);
		return url;
	}
}
