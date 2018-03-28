package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;


public interface CatalogDao {

	List<ProductCatalog> getActiveCatalog(Integer siteId);

	void updateActiveCatalog(Catalog catalog);
	
	Catalog getCatalogById(Integer siteId, Integer productId);
	
}
