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

import com.hdsupply.xmi.domain.Location;
import com.hdsupply.xmi.service.LocationService;

import junit.framework.AssertionFailedError;

@ContextConfiguration(classes = LocationControllerTest.class)
public class LocationControllerTest extends ControllerTestBase {
	
	@Autowired
	private LocationService mockLocationService;	
	
	@Before
	public void setUp() throws Exception {
		
		super.setUp();
		
        EasyMock.reset(mockLocationService);
	}	

	@Test
	@WithMockUser(username = "admin", authorities = { "GET_LOCATIONS" })
	public void testGetLocationsByShop() throws Exception {
		
		Location location1 = new Location();
		location1.setId(111);
		location1.setName("Test Name 1");
		location1.setShopId(123);

		Location location2 = new Location();
		location2.setId(222);
		location2.setName("Test Name 2");
		location2.setShopId(321);
		
		List<Location> returnedLocations = Arrays.asList(new Location[] {location1, location2});
		
		EasyMock.expect(mockLocationService.getLocationsByShop(2)).andReturn(returnedLocations);
	
		EasyMock.replay(mockLocationService);
		
		File file = ResourceUtils.getFile("classpath:json/getLocationsByShop.json");
		String expectedJson = new String(Files.readAllBytes(file.toPath()));			
		
		mockMvc.perform(get("/rest/shop/2/location")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockLocationService);		
		
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION" })
	public void testGetAllPlacesUnauthorized() throws Exception {
		
		EasyMock.expect(mockLocationService.getLocationsByShop(EasyMock.anyInt()))
			.andThrow(new AssertionFailedError())
			.anyTimes();
	
		EasyMock.replay(mockLocationService);
		
		mockMvc.perform(get("/rest/shop/2/location")
			.header("Accept", "application/json"))
			.andExpect(status().isForbidden());
		
		EasyMock.verify(mockLocationService);		
		
	}
	
	
	@Bean
	public LocationService locationService() {
		return EasyMock.createStrictMock(LocationService.class);
	}
  
	@Bean
	public LocationController fixture() {
		return new LocationController();
	}	

}
