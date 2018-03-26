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

import com.hdsupply.xmi.domain.Place;
import com.hdsupply.xmi.service.PlaceService;

import junit.framework.AssertionFailedError;

@ContextConfiguration(classes = AssetControllerTest.class)
public class AssetControllerTest extends ControllerTestBase {
	
	@Autowired
	private PlaceService mockPlaceService;	
	
	@Before
	public void setUp() throws Exception {
		
		super.setUp();
		
        EasyMock.reset(mockPlaceService);
	}	

	@Test
	@WithMockUser(username = "admin", authorities = { "GET_PLACES" })
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
		
		mockMvc.perform(get("/rest/place")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockPlaceService);		
		
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION" })
	public void testGetAllPlacesUnauthorized() throws Exception {
		
		EasyMock.expect(mockPlaceService.getActivePlaces()).andThrow(new AssertionFailedError())
			.anyTimes();
	
		EasyMock.replay(mockPlaceService);
		
		mockMvc.perform(get("/rest/place")
			.header("Accept", "application/json"))
			.andExpect(status().isForbidden());
		
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
