package com.hdsupply.xmi.service;

import java.util.List;

import com.hdsupply.xmi.domain.Place;

public interface PlaceService {

	/**
	 * Constructs a list of basic Asset based on the corresponding
	 * Beacon.
	 * 
	 * @param beacons
	 * @return
	 */
	List<Place> getActivePlaces();

}