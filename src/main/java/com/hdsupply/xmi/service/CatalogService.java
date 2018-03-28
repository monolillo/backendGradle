package com.hdsupply.xmi.service;

import java.util.List;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;

public interface CatalogService {

	List<ProductCatalog> getActiveCatalog(Integer siteId);
	
	void updateActiveCatalog(Catalog catalog);
	
}
