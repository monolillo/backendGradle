package com.hdsupply.xmi.repository;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = IftttDaoRestImplTest.class)
public class IftttDaoRestImplTest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IftttDao fixture;
	
	protected MockRestServiceServer mockServer;
	
	@Before
	public void setUp() throws Exception {
		
		mockServer = MockRestServiceServer.createServer(restTemplate);
		
	}		

	@Test
	public void testTiggerEvent() {
		
		mockServer.expect(requestTo("https://maker.ifttt.com/trigger/EVENT/with/key/d8MYovssL5-X7KHvmiIzmN"))
			.andExpect(method(HttpMethod.POST))
			.andExpect(content().string("{\"value2\":\"VAL2\",\"value1\":\"VAL1\",\"value3\":\"VAL3\"}"))
	     	.andRespond(withSuccess("Hello", MediaType.APPLICATION_JSON));
		
		fixture.tiggerEvent("EVENT", "VAL1", "VAL2", "VAL3");
		 
		mockServer.verify();

	}
	
	@Test(expected = Exception.class)
	public void testTiggerEventServerError() {
		
		mockServer.expect(requestTo("https://maker.ifttt.com/trigger/EVENT/with/key/d8MYovssL5-X7KHvmiIzmN"))
			.andExpect(method(HttpMethod.POST))
			.andExpect(content().string("{\"value2\":\"VAL2\",\"value1\":\"VAL1\",\"value3\":\"VAL3\"}"))
	     	.andRespond(withServerError());
		
		fixture.tiggerEvent("EVENT", "VAL1", "VAL2", "VAL3");
		 
		mockServer.verify();

	}	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public IftttDao fixture() {
		return new IftttDaoRestImpl();
	}		

}
