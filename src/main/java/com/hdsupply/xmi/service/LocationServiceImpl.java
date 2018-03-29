package com.hdsupply.xmi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Location;
import com.hdsupply.xmi.repository.LocationDao;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;
	
	public List<Location> getLocationsByShop(int shopId) {
		return locationDao.getLocationsByShop(shopId);
	}

	

}
