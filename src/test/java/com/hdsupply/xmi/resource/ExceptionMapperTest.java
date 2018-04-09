package com.hdsupply.xmi.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.ProductService;

import junit.framework.AssertionFailedError;

@ContextConfiguration(classes = {ExceptionMapperTest.class, RestControllerExceptionMapper.class})
public class ExceptionMapperTest extends ControllerTestBase {

	@Autowired
	private CatalogService mockCatalogService;
	
	@Before
	public void setUp() throws Exception {
		
        super.setUp();		
        
        EasyMock.reset(mockCatalogService);
	}	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testJsonFormatException() throws Exception {
		
		mockCatalogService.updateActiveCatalog(EasyMock.anyObject(Catalog.class));

		EasyMock.expectLastCall().andThrow(new AssertionFailedError()).anyTimes();	
	
		EasyMock.replay(mockCatalogService);
		
		File file = ResourceUtils.getFile("classpath:request/invalidJson.txt");
		String jsonRequest = new String(Files.readAllBytes(file.toPath()));
		
		file = ResourceUtils.getFile("classpath:json/invalidJsonResponse.json");
		String response = new String(Files.readAllBytes(file.toPath()));		
		
		mockMvc.perform(put("/rest/site/2/product/1")
			.header("Accept", "application/json")
			.header("Content-type", "application/json")
			.content(jsonRequest))
			.andExpect(status().isBadRequest())
			.andExpect(content().json(response));
			
		EasyMock.verify(mockCatalogService);		
		
	}	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testInvalidContentType() throws Exception {
		
		mockCatalogService.updateActiveCatalog(EasyMock.anyObject(Catalog.class));

		EasyMock.expectLastCall().andThrow(new AssertionFailedError()).anyTimes();	
	
		EasyMock.replay(mockCatalogService);
		
		File file = ResourceUtils.getFile("classpath:request/requestUpdateActiveCatalog.json");
		String jsonRequest = new String(Files.readAllBytes(file.toPath()));
		
		file = ResourceUtils.getFile("classpath:json/invalidContentTypeResponse.json");
		String response = new String(Files.readAllBytes(file.toPath()));		
		
		mockMvc.perform(put("/rest/site/2/product/1")
			.header("Accept", "application/json")
			.header("Content-type", "application/javascript")
			.content(jsonRequest))
			.andExpect(status().isBadRequest())
			.andExpect(content().json(response));
			
		EasyMock.verify(mockCatalogService);		
		
	}	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testException() throws Exception {
		
		mockCatalogService.updateActiveCatalog(EasyMock.anyObject(Catalog.class));

		EasyMock.expectLastCall().andThrow(new RuntimeException("This is the message."));	
	
		EasyMock.replay(mockCatalogService);
		
		File file = ResourceUtils.getFile("classpath:request/requestUpdateActiveCatalog.json");
		String jsonRequest = new String(Files.readAllBytes(file.toPath()));
		
		file = ResourceUtils.getFile("classpath:json/exceptionResponse.json");
		String response = new String(Files.readAllBytes(file.toPath()));			
		
		mockMvc.perform(put("/rest/site/2/product/1")
			.header("Accept", "application/json")
			.header("Content-type", "application/json")
			.content(jsonRequest))
			.andExpect(status().isInternalServerError())
			.andExpect(content().json(response));
			
		EasyMock.verify(mockCatalogService);		
		
	}		
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testInvalidValues() throws Exception {
		
		mockCatalogService.updateActiveCatalog(EasyMock.anyObject(Catalog.class));

		EasyMock.expectLastCall().andThrow(new AssertionFailedError()).anyTimes();
	
		EasyMock.replay(mockCatalogService);
		
		File file = ResourceUtils.getFile("classpath:request/requestUpdateActiveCatalogInvalidValue.json");
		String jsonRequest = new String(Files.readAllBytes(file.toPath()));
		
		file = ResourceUtils.getFile("classpath:json/invalidValueResponse.json");
		String response = new String(Files.readAllBytes(file.toPath()));			
		
		mockMvc.perform(put("/rest/site/2/product/1")
			.header("Accept", "application/json")
			.header("Content-type", "application/json")
			.content(jsonRequest))
			.andExpect(status().isBadRequest())
			.andExpect(content().json(response));
			
		EasyMock.verify(mockCatalogService);		
		
	}			
	
	@Bean
	public CatalogService catalogService() {
		return EasyMock.createStrictMock(CatalogService.class);
	}
  
	@Bean
	public ProductService productService() {
		return EasyMock.createStrictMock(ProductService.class);
	}
	
	@Bean
	public CatalogController fixture() {
		return new CatalogController();
	}	
	
}

