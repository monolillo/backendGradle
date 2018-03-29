package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Location;

@ContextConfiguration(classes=LocationDaoDbImplTest.class)
@Configuration
public class LocationDaoDbImplTest extends DaoDbTestBase {
	
	@Autowired
	private LocationDao fixture;	

	@Test
	public void testGetActivePlaces() {
		
		List<Location> locations = fixture.getLocationsByShop(2);
		
		assertEquals("ID should match first record", Integer.valueOf(2), locations.get(0).getId());
		assertEquals("Name should match first record", "A1", locations.get(0).getName());

		assertEquals("ID should match second record", Integer.valueOf(3), locations.get(1).getId());
		assertEquals("Name should match second record", "A2", locations.get(1).getName());
		
		assertEquals("Name of records should match DB", 4, locations.size());
		
	}
	
	@Bean
	public LocationDao fixture() {
		return new LocationDaoDbImpl();
	}

}
