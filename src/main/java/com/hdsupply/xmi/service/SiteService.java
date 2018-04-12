package com.hdsupply.xmi.service;

import java.util.List;

import com.hdsupply.xmi.domain.Site;

public interface SiteService {
	
	public List<Site> getSiteByUser(String user);
	

}
