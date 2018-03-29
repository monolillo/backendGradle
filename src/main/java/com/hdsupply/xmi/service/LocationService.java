package com.hdsupply.xmi.service;

import java.util.List;

import com.hdsupply.xmi.domain.Location;

public interface LocationService {

	/**
	 * Constructs a list of basic Asset based on the corresponding
	 * Beacon.
	 * 
	 * @param beacons
	 * @return
	 */
	List<Location> getLocationsByShop(int shopId);

}