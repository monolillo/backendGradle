package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.Catalog;


public interface CatalogDao {

	List<Catalog> getActiveCatalog(Integer siteId);
	
}
