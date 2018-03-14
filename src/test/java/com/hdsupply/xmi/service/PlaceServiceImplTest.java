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

import com.hdsupply.xmi.domain.Place;
import com.hdsupply.xmi.repository.PlaceDao;

@RunWith(EasyMockRunner.class)
public class PlaceServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private PlaceServiceImpl fixture = new PlaceServiceImpl();	
	
	@Mock
	private PlaceDao placeDao;	

	@Test
	public void testGetActivePlaces() {
		
		List<Place> returnedList = new ArrayList<Place>();
		EasyMock.expect(placeDao.getActivePlaces()).andReturn(returnedList);
		
		replayAll();
		List<Place> result = fixture.getActivePlaces();
		verifyAll();
		
		assertEquals("Returned list should match the one returned by DAO.", returnedList, result);
	}

}
