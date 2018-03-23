package com.hdsupply.xmi.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.ProductService;

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

