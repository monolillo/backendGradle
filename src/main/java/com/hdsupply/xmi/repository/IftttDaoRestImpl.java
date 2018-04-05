package com.hdsupply.xmi.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

public class IftttDaoRestImpl implements IftttDao {
	
	@Autowired
	private RestOperations restTemplate;
	
	private final static String key = "d8MYovssL5-X7KHvmiIzmN";
	private final static String endpoint = "https://maker.ifttt.com/trigger/{eventName}/with/key/{key}";
	
	private final static String VALUE1_KEY = "value1";
	private final static String VALUE2_KEY = "value2";
	private final static String VALUE3_KEY = "value3";

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

		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(body, headers);
		
		restTemplate.postForObject(endpoint, entity, String.class, eventName, key);

	}

}
