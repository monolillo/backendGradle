package com.hdsupply.xmi.repository;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = AzureBlobDaoRestImplTest.class)
public class AzureBlobDaoRestImplTest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AzureBlobDao fixture;
	
	protected MockRestServiceServer mockServer;
	
	@Before
	public void setUp() throws Exception {
		
		mockServer = MockRestServiceServer.createServer(restTemplate);
		
	}		

	@Test
	public void testTiggerEvent() {
		
		String content = "This is the content";
		
		mockServer.expect(requestTo("https://elux1.blob.core.windows.net/xmistorage/testFile.xlsx"))
			.andExpect(method(HttpMethod.PUT))
			.andExpect(content().bytes(content.getBytes()))
			.andExpect(header("Content-Type", "application/test"))
			.andExpect(header("Content-Length", "19"))
			.andExpect(header("x-ms-blob-type", "BlockBlob"))
			.andExpect(header("x-ms-version", "2015-12-11"))
	     	.andRespond(withSuccess());
		
		fixture.uploadBlob("testFile.xlsx", content.getBytes(), "application/test");
		 
		mockServer.verify();
	
	}
	
	@Test
	public void testTiggerEventSpaceInFilename() {
		
		String content = "This is the content";
		
		mockServer.expect(requestTo("https://elux1.blob.core.windows.net/xmistorage/test%20file.xlsx"))
			.andExpect(method(HttpMethod.PUT))
			.andExpect(content().bytes(content.getBytes()))
			.andExpect(header("Content-Type", "application/test"))
			.andExpect(header("Content-Length", "19"))
			.andExpect(header("x-ms-blob-type", "BlockBlob"))
			.andExpect(header("x-ms-version", "2015-12-11"))
	     	.andRespond(withSuccess());
		
		fixture.uploadBlob("test file.xlsx", content.getBytes(), "application/test");
		 
		mockServer.verify();
	
	}
	
	@Test(expected=Exception.class)
	public void testTiggerEventAzureError() {
		
		String content = "This is the content";
		
		mockServer.expect(requestTo("https://elux1.blob.core.windows.net/xmistorage/testFile.xlsx"))
			.andExpect(method(HttpMethod.PUT))
			.andExpect(content().bytes(content.getBytes()))
			.andExpect(header("Content-Type", "application/test"))
			.andExpect(header("Content-Length", "19"))
			.andExpect(header("x-ms-blob-type", "BlockBlob"))
			.andExpect(header("x-ms-version", "2015-12-11"))
	     	.andRespond(withServerError());
		
		fixture.uploadBlob("testFile.xlsx", content.getBytes(), "application/test");
		 
		mockServer.verify();
	
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AzureBlobDao fixture() {
		return new AzureBlobDaoRestImpl();
	}		

}
