package com.hdsupply.xmi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.service.SiteService;

@RestController
public class SiteController extends BaseRestController{
	
	@Autowired
	private SiteService siteService;

	@RequestMapping(value="/user/{user}/site")
	@PreAuthorize("hasAuthority('USER_SITE')")
	public List<Site> getSiteByUser(@PathVariable("user") String user){
		
		return siteService.getSiteByUser(user);
	}

}

