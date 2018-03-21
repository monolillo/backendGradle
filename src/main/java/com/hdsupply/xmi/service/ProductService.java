package com.hdsupply.xmi.service;

import com.hdsupply.xmi.domain.ProductCatalog;

public interface ProductService {

	ProductCatalog getProductById(Integer siteId, Integer pruductId);
	
}
