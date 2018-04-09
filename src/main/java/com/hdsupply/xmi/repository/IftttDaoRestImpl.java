package com.hdsupply.xmi.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

@Repository
public class IftttDaoRestImpl implements IftttDao {
	
	@Autowired
	private RestOperations restTemplate;
	
	private static final String API_KEY = "d8MYovssL5-X7KHvmiIzmN";
	private static final String ENDPOINT = "https://maker.ifttt.com/trigger/{eventName}/with/key/{key}";
	
	private static final String VALUE1_KEY = "value1";
	private static final String VALUE2_KEY = "value2";
	private static final String VALUE3_KEY = "value3";

	/**
	 * Sends a request to the IFTTT endpoint with the following JSON body
	 * 
	 * { 
	 * 	"value1" : "{value1}",
	 *	"value2" : "{value2}",
	 *  "value3" : "{value3}" 
	 * }
	 * 
	 */
	@Override
	public void tiggerEvent(String eventName, String value1, String value2, String value3) {
		
		Map<String, String> body = new HashMap<>();
		body.put(VALUE1_KEY, value1);
		body.put(VALUE2_KEY, value2);
		body.put(VALUE3_KEY, value3);		
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
		
		restTemplate.postForObject(ENDPOINT, entity, String.class, eventName, API_KEY);

	}

}
