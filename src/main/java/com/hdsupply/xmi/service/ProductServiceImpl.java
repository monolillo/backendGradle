package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private InventoryDao inventoryDao;

	@Override
	public ProductCatalog getProductById(Integer siteId, Integer pruductId) {
		
		ProductCatalog productCatalog = productDao.getProductById(siteId, pruductId);
		
		Integer qty = inventoryDao.getQuantity(pruductId, siteId);
		
		productCatalog.setQuantity(qty);
		
		return productCatalog;
	}
	
}
