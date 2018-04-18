package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.Location;
import com.hdsupply.xmi.repository.LocationDao;

@RunWith(EasyMockRunner.class)
public class LocationServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private LocationServiceImpl fixture = new LocationServiceImpl();	
	
	@Mock
	private LocationDao locationDao;	

	@Test
	public void testGetActivePlaces() {
		
		List<Location> returnedList = new ArrayList<Location>();
		EasyMock.expect(locationDao.getLocationsByShop(2)).andReturn(returnedList);
		
		replayAll();
		List<Location> result = fixture.getLocationsByShop(2);
		verifyAll();
		
		assertEquals("Returned list should match the one returned by DAO.", returnedList, result);
	}

}
