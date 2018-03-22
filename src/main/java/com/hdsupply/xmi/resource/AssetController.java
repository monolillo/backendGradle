package com.hdsupply.xmi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Place;
import com.hdsupply.xmi.service.PlaceService;

/**
 * To aid the example of the JDBC connectivity
 *
 * @author Julian F. Nunez <vc.julian.nune@lowes.com>
 * @created Apr 17, 2015
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest")
public class AssetController {
	
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping(value="/place")
	@PreAuthorize("hasAuthority('GET_PLACES')")
	public List<Place> getAllPlaces(@AuthenticationPrincipal User principal) {
		
		System.out.println(principal.getUsername());
		
		List<Place> places = placeService.getActivePlaces();
		
		return places;
	}
	
}

