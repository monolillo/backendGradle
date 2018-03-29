package com.hdsupply.xmi.resource;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.ProductService;

import junit.framework.AssertionFailedError;

@ContextConfiguration(classes = {CatalogControllerTest.class})
public class CatalogControllerTest extends ControllerTestBase {

	@Autowired
	private CatalogService mockCatalogService;
	
	@Autowired
	private ProductService mockProductService;
	
	@Before
	public void setUp() throws Exception {
		
        super.setUp();		
        
        EasyMock.reset(mockCatalogService);
        EasyMock.reset(mockProductService);
	}	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testGetActiveCatalog() throws Exception {
		
		ProductCatalog productCatalog1 = new ProductCatalog();
		productCatalog1.setIdProduct(123);
		productCatalog1.setItemNumber(1);
		productCatalog1.setMax(10);
		productCatalog1.setMin(2);
		productCatalog1.setName("A Bulb 40W A15 Frost");
		
		ProductCatalog productCatalog2 = new ProductCatalog();
		productCatalog2.setIdProduct(321);
		productCatalog2.setItemNumber(2);
		productCatalog2.setMax(9);
		productCatalog2.setMin(2);
		productCatalog2.setName("T8 Bulb 40W Intermediate");
		
		List<ProductCatalog> returnedProductCatalog = Arrays.asList(new ProductCatalog[] {productCatalog1, productCatalog2});
		
		EasyMock.expect(mockCatalogService.getActiveCatalog(2)).andReturn(returnedProductCatalog);
		
		EasyMock.replay(mockCatalogService);
		
		File file = ResourceUtils.getFile("classpath:json/getActiveCatalog.json");
		String expectedJson = new String(Files.readAllBytes(file.toPath()));	
		
		mockMvc.perform(get("/rest/site/2/product")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockCatalogService);
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testGetProductById() throws Exception {
		
		ProductCatalog productCatalog = new ProductCatalog();
		productCatalog.setIdProduct(123);
		productCatalog.setName("A Bulb 40W A15 Frost");
		productCatalog.setItemNumber(2);
		productCatalog.setMax(10);
		productCatalog.setMin(2);

		EasyMock.expect(mockProductService.getProductById(2,1)).andReturn(productCatalog);
		
		EasyMock.replay(mockProductService);
		
		File file = ResourceUtils.getFile("classpath:json/getProductById.json");
		String expectedJson1 = new String(Files.readAllBytes(file.toPath()));
		
		mockMvc.perform(get("/rest/site/2/product/1")
				.header("Accept", "application/json"))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJson1));
			
			EasyMock.verify(mockProductService);
	}

	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testGetProductByIdNotFound() throws Exception {
		
		EasyMock.expect(mockProductService.getProductById(2,100)).andReturn(null);
		
		EasyMock.replay(mockProductService);
		
		mockMvc.perform(get("/rest/site/2/product/100")
				.header("Accept", "application/json"))
				.andExpect(status().isNotFound());
			
			EasyMock.verify(mockProductService);
	}	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "READ_SITE_CATALOG" })
	public void testUpdateActiveCatalog() throws Exception{
		
		Catalog catalog = new Catalog();
		
		catalog.setMin(1);
		catalog.setMax(1);
		catalog.setCritical(true);
		
		Capture<Catalog> captured = EasyMock.newCapture();
		
		mockCatalogService.updateActiveCatalog(EasyMock.capture(captured));
		EasyMock.replay(mockCatalogService);
		
		File file = ResourceUtils.getFile("classpath:request/requestUpdateActiveCatalog.json");
		String requestBody = new String(Files.readAllBytes(file.toPath()));
		
		mockMvc.perform(put("/rest/site/2/product/1").
				contentType(MediaType.APPLICATION_JSON_UTF8).
				content(requestBody)
				).andExpect(status().isNoContent());

		EasyMock.verify(mockCatalogService);
		
		assertEquals(catalog.getMin(), captured.getValue().getMin());
		assertEquals(catalog.getMax(), captured.getValue().getMax());
		assertEquals(catalog.getCritical(), captured.getValue().getCritical());
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION" })
	public void testGetActiveCatalogUnauthorized() throws Exception {
		
		EasyMock.expect(mockCatalogService.getActiveCatalog(2)).andThrow(new AssertionFailedError())
			.anyTimes();
	
		EasyMock.replay(mockCatalogService);
		
		mockMvc.perform(get("/rest/site/2/product")
			.header("Accept", "application/json"))
			.andExpect(status().isForbidden());
		
		EasyMock.verify(mockCatalogService);		
		
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION" })
	public void testGetProductByIdUnauthorized() throws Exception {
		
		EasyMock.expect(mockProductService.getProductById(2, 1)).andThrow(new AssertionFailedError())
			.anyTimes();
	
		EasyMock.replay(mockProductService);
		
		mockMvc.perform(get("/rest/site/2/product")
			.header("Accept", "application/json"))
			.andExpect(status().isForbidden());
		
		EasyMock.verify(mockProductService);		
		
	}
	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION"})
	public void testUpdateActiveCatalogUnauthorized() throws Exception {
		
		File file = ResourceUtils.getFile("classpath:request/requestUpdateActiveCatalog.json");
		String requestBody = new String(Files.readAllBytes(file.toPath()));
		
		mockMvc.perform(put("/rest/site/2/product/1").
				contentType(MediaType.APPLICATION_JSON_UTF8).
				content(requestBody)
				).andExpect(status().isForbidden());

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

