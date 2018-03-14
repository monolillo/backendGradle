package com.hdsupply.xmi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Place;
import com.hdsupply.xmi.repository.PlaceDao;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	@Autowired
	private PlaceDao placeDao;
	
	public List<Place> getActivePlaces() {
		return placeDao.getActivePlaces();
	}

	

}
