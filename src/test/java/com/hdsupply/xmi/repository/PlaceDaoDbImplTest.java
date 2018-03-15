package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Place;

@ContextConfiguration(classes=PlaceDaoDbImplTest.class)
@Configuration
public class PlaceDaoDbImplTest extends DaoDbTestBase {
	
	@Autowired
	private PlaceDao fixture;	

	@Test
	public void testGetActivePlaces() {
		
		List<Place> places = fixture.getActivePlaces();
		
		assertEquals("ID should match first record", 1, places.get(0).getId());
		assertEquals("Name should match first record", "Rojas", places.get(0).getName());

		assertEquals("ID should match second record", 2, places.get(1).getId());
		assertEquals("Name should match second record", "Mooresville", places.get(1).getName());
		
	}
	
	@Bean
	public PlaceDao fixture() {
		return new PlaceDaoDbImpl();
	}

}
