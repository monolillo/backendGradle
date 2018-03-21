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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hdsupply.xmi.domain.Place;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.PlaceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CatalogControllerTest.class})
@WebAppConfiguration
@Configuration
@EnableWebMvc
public class CatalogControllerTest {

	@Autowired
	private CatalogService mockCatalogService;
	
	@Autowired
    private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		
		// Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();		
        
        EasyMock.reset(mockCatalogService);
	}	
	
	@Test
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
		
		mockMvc.perform(get("/site/2/product")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockCatalogService);
	}
	
	@Bean
	public CatalogService catalogService() {
		return EasyMock.createStrictMock(CatalogService.class);
	}
  
	@Bean
	public CatalogController fixture() {
		return new CatalogController();
	}	
	
}

