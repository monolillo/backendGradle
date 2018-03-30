package com.hdsupply.xmi.repository;

import com.hdsupply.xmi.domain.ProductCatalog;

public interface ProductDao {
	
	ProductCatalog getProductById(Integer siteId, Integer productId);
	
	ProductCatalog getProductByItemNumber(Integer siteId, Integer itemNumber);

}
