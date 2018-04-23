package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.Site;

public interface SiteDao {

	List<Site> getSiteByUser(String user);
	
	Site getSiteByIdShop(Integer shopId);
	
}
