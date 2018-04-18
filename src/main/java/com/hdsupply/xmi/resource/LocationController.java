package com.hdsupply.xmi.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Location;
import com.hdsupply.xmi.service.LocationService;

/**
 * Serves requests to /rest/shop/123/location
 *
 * @author Julian F. Nunez <julian.nunez@neoris.com>
 * @created Mar 28, 2018
 */
@RestController
public class LocationController extends BaseRestController {
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value="/shop/{shopId}/location")
	@PreAuthorize("hasAuthority('GET_LOCATIONS')")
	public List<Location> getLocationsByShop(Principal userDetails, @PathVariable("shopId") Integer shopId) {
		
		List<Location> locations = locationService.getLocationsByShop(shopId);
		
		return locations;
	}
	
}

