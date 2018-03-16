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
import com.hdsupply.xmi.service.PlaceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AssetControllerTest.class})
@WebAppConfiguration
@Configuration
@EnableWebMvc
public class AssetControllerTest {
	
	@Autowired
	private PlaceService mockPlaceService;	
	
    @Autowired
    private WebApplicationContext ctx;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		
		// Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();		
        
        EasyMock.reset(mockPlaceService);
	}	

	@Test
	public void testGetAllPlaces() throws Exception {
		
		Place place1 = new Place();
		place1.setId(123);
		place1.setName("Test Name");

		Place place2 = new Place();
		place2.setId(321);
		place2.setName("Test Name 2");
		
		List<Place> returnedPlaces = Arrays.asList(new Place[] {place1, place2});
		
		EasyMock.expect(mockPlaceService.getActivePlaces()).andReturn(returnedPlaces);
	
		EasyMock.replay(mockPlaceService);
		
		File file = ResourceUtils.getFile("classpath:json/getAllPlaces.json");
		String expectedJson = new String(Files.readAllBytes(file.toPath()));			
		
		mockMvc.perform(get("/place")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockPlaceService);		
		
	}
	
	
	@Bean
	public PlaceService placeService() {
		return EasyMock.createStrictMock(PlaceService.class);
	}
  
	@Bean
	public AssetController fixture() {
		return new AssetController();
	}	

}
